/*
 * Created by Muhamad Syafii
 * 20/7/2025 - muhamadsyafii4@gmail.com
 * Copyright (c) 2025.
 * All Rights Reserved
 */

package dev.kupil.kupildown.database

import dev.kupil.kupildown.database.entity.DownloadModel

interface DbHelper {
  suspend fun find(id: Int): DownloadModel?
  suspend fun insert(model: DownloadModel)
  suspend fun update(model: DownloadModel)
  suspend fun updateProgress(id: Int, downloadedBytes: Long, lastModifiedAt: Long)
  suspend fun remove(id: Int)
  suspend fun getUnwantedModels(days: Int): List<DownloadModel>?
  suspend fun empty()
  suspend fun close()

}