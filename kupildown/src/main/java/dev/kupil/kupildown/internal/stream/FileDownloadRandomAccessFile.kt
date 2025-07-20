/*
 * Created by Muhamad Syafii
 * 20/7/2025 - muhamadsyafii4@gmail.com
 * Copyright (c) 2025.
 * All Rights Reserved
 */

package dev.kupil.kupildown.internal.stream

import java.io.BufferedOutputStream
import java.io.File
import java.io.FileDescriptor
import java.io.FileOutputStream
import java.io.IOException
import java.io.RandomAccessFile

class FileDownloadRandomAccessFile private constructor(file: File) : FileDownloadOutputStream {
  private val out: BufferedOutputStream
  private val fd: FileDescriptor
  private val randomAccess: RandomAccessFile

  init {
    randomAccess = RandomAccessFile(file, "rw")
    fd = randomAccess.fd
    out = BufferedOutputStream(FileOutputStream(randomAccess.fd))
  }

  @Throws(IOException::class)
  override fun write(b: ByteArray?, off: Int, len: Int) {
    out.write(b, off, len)
  }

  @Throws(IOException::class)
  override fun flushAndSync() {
    out.flush()
    fd.sync()
  }

  @Throws(IOException::class)
  override fun close() {
    out.close()
    randomAccess.close()
  }

  @Throws(IOException::class)
  override fun seek(offset: Long) {
    randomAccess.seek(offset)
  }

  @Throws(IOException::class)
  override fun setLength(newLength: Long) {
    randomAccess.setLength(newLength)
  }

  companion object {
    @Throws(IOException::class)
    fun create(file: File): FileDownloadOutputStream {
      return FileDownloadRandomAccessFile(file)
    }
  }
}