/*
 * Created by Muhamad Syafii
 * 20/7/2025 - muhamadsyafii4@gmail.com
 * Copyright (c) 2025.
 * All Rights Reserved
 */

package dev.kupil.kupildown.utils

data class DownloaderConfig(
  var databaseEnabled: Boolean = false,
  var connectTimeOut : Int = Constants.DEFAULT_CONNECT_TIMEOUT_IN_MILLS,
  var readTimeOut: Int = Constants.DEFAULT_READ_TIMEOUT_IN_MILLS
)