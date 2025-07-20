/*
 * Created by Muhamad Syafii
 * 20/7/2025 - muhamadsyafii4@gmail.com
 * Copyright (c) 2025.
 * All Rights Reserved
 */

package dev.kupil.kupildown

import android.content.Context
import android.util.Log
import dev.kupil.kupildown.database.DbHelper
import dev.kupil.kupildown.database.NoOpsDbHelper
import dev.kupil.kupildown.internal.DownloadDispatchers
import dev.kupil.kupildown.internal.DownloadRequest
import dev.kupil.kupildown.internal.DownloadRequestQueue
import dev.kupil.kupildown.utils.DownloaderConfig
import dev.kupil.kupildown.utils.Status
import kotlinx.coroutines.runBlocking

class KupilDownloader private constructor(
  private val dbHelper: DbHelper,
  private val config: DownloaderConfig
) {

  companion object {
    fun create(
      context: Context,
      config: DownloaderConfig = DownloaderConfig(true)
    ): KupilDownloader {
      return if (config.databaseEnabled) {
        KupilDownloader(AppDbHelper(context), config)
      } else {
        KupilDownloader(NoOpsDbHelper(),config)
      }
    }
  }

  private val downloader = DownloadDispatchers(dbHelper)
  private val reqQueue = DownloadRequestQueue(downloader)

  fun newRequestBuilder(url: String, dirPath: String, fileName: String): DownloadRequest.Builder {
    return DownloadRequest.Builder(url, dirPath, fileName)
      .readTimeout(config.readTimeOut)
      .connectTimeout(config.connectTimeOut)
  }

  fun enqueue(req: DownloadRequest, listener: DownloadRequest.Listener): Int {
    req.listener = listener
    return reqQueue.enqueue(req)
  }

  inline fun enqueue(
    req: DownloadRequest,
    crossinline onStart: () -> Unit = {},
    crossinline onProgress: (value: Int) -> Unit = { _ -> },
    crossinline onPause: () -> Unit = {},
    crossinline onError: (error: String) -> Unit = { _ -> },
    crossinline onCompleted: () -> Unit = {}
  ) = enqueue(req, object : DownloadRequest.Listener {
    override fun onStart() = onStart()
    override fun onProgress(value: Int) = onProgress(value)
    override fun onPause() = onPause()
    override fun onError(error: String) = onError(error)
    override fun onCompleted() = onCompleted()
  })

  fun status(id: Int): Status {
    return reqQueue.status(id)
  }

  fun cancel(id: Int) {
    reqQueue.cancel(id)
  }

  fun cancel(tag: String) {
    reqQueue.cancel(tag)
  }

  fun cancelAll() {
    reqQueue.cancelAll()
  }

  fun pause(id: Int) {
    reqQueue.pause(id)
  }

  fun resume(id: Int) {
    reqQueue.resume(id)
  }

  fun cleanUp(days: Int) {
    downloader.cleanup(days)

  }

  fun release() {
    runBlocking {
      try {
        dbHelper.close()
      } catch (e: Exception) {
        Log.e("KupilDownloader", "Error closing DB", e)
      }
    }
  }
}