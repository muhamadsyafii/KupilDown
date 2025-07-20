/*
 * Created by Muhamad Syafii
 * 20/7/2025 - muhamadsyafii4@gmail.com
 * Copyright (c) 2025.
 * All Rights Reserved
 */

package dev.kupil.kupildown

import android.app.Application

class KupilApp : Application() {

  lateinit var kupilDownloader: KupilDownloader

  override fun onCreate() {
    super.onCreate()
    kupilDownloader = KupilDownloader.create(this)
  }

}