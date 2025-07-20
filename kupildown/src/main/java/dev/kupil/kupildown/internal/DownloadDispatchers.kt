/*
 * Created by Muhamad Syafii
 * 20/7/2025 - muhamadsyafii4@gmail.com
 * Copyright (c) 2025.
 * All Rights Reserved
 */

package dev.kupil.kupildown.internal

import dev.kupil.kupildown.database.DbHelper
import dev.kupil.kupildown.database.entity.DownloadModel
import dev.kupil.kupildown.utils.Status
import dev.kupil.kupildown.utils.getTempPath
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.File

class DownloadDispatchers(private val dbHelper: DbHelper) {

  private val scope = CoroutineScope(
    SupervisorJob() + Dispatchers.Main +
    CoroutineExceptionHandler { _, _ ->

    })

  private val dbScope = CoroutineScope(
    SupervisorJob() + Dispatchers.IO +
    CoroutineExceptionHandler { _, _ ->

    })

  fun enqueue(req: DownloadRequest): Int {
    val job = scope.launch {
      execute(req)
    }
    req.job = job
    return req.downloadId
  }

  private suspend fun execute(request: DownloadRequest) {
    DownloadTask(request, dbHelper).run(
      onStart = {
        executeOnMainThread { request.listener?.onStart() }
      },
      onProgress = {
        executeOnMainThread { request.listener?.onProgress(it) }
      },
      onPause = {
        executeOnMainThread { request.listener?.onPause() }
      },
      onCompleted = {
        executeOnMainThread { request.listener?.onCompleted() }
      },
      onError = {
        executeOnMainThread { request.listener?.onError(it) }
      }
    )
  }

  private fun executeOnMainThread(block: () -> Unit) {
    scope.launch {
      block()
    }
  }

  fun cancel(req: DownloadRequest) {

    if (req.status == Status.PAUSED) {
      val tempPath = getTempPath(req.dirPath, req.fileName)
      val file = File(tempPath)
      if (file.exists()) {
        file.delete()
      }
      req.reset()
    }

    req.status = Status.CANCELLED
    req.job.cancel()

    req.listener?.onError("Cancelled")

    dbScope.launch {
      dbHelper.remove(req.downloadId)
    }
  }

  fun cancelAll() {
    scope.cancel()
    dbScope.launch {
      dbHelper.empty()
    }
  }

  fun cleanup(days: Int) {
    dbScope.launch {
      val models: List<DownloadModel>? = dbHelper.getUnwantedModels(days)
      if (models != null) {
        for (model in models) {
          val tempPath: String = getTempPath(
            model.dirPath,
            model.fileName
          )
          dbHelper.remove(model.id)
          val file = File(tempPath)
          if (file.exists()) {
            file.delete()
          }
        }
      }
    }
  }
}