/*
 * Created by Muhamad Syafii
 * 20/7/2025 - muhamadsyafii4@gmail.com
 * Copyright (c) 2025.
 * All Rights Reserved
 */

package dev.kupil.kupildown.httpclient

import dev.kupil.kupildown.internal.DownloadRequest
import java.io.IOException
import java.io.InputStream

interface HttpClient : Cloneable {
  fun getContentLength(): Long
  fun getHeaderFields(): Map<String, List<String>>
  @Throws(IOException::class)
  fun getResponseCode(): Int
  @Throws(IOException::class)
  fun getInputStream(): InputStream?
  fun getErrorStream(): InputStream?
  public override fun clone(): HttpClient
  @Throws(IOException::class)
  fun connect(req: DownloadRequest)
  fun getResponseHeader(name: String): String
  fun close()
}