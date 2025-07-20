/*
 * Created by Muhamad Syafii
 * 20/7/2025 - muhamadsyafii4@gmail.com
 * Copyright (c) 2025.
 * All Rights Reserved
 */

package dev.kupil.kupildown.database

import dev.kupil.kupildown.database.entity.DownloadModel

class NoOpsDbHelper : DbHelper {

  override suspend fun find(id: Int): DownloadModel? {
    return null
  }

  override suspend fun insert(model: DownloadModel) {
  }

  override suspend fun update(model: DownloadModel) {
  }

  override suspend fun updateProgress(id: Int, downloadedBytes: Long, lastModifiedAt: Long) {
  }

  override suspend fun remove(id: Int) {
  }

  override suspend fun getUnwantedModels(days: Int): List<DownloadModel>? {
    return null
  }

  override suspend fun empty() {

  }

  override suspend fun close() {

  }
}