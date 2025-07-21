# KupilDown

**KupilDownloader** is a lightweight and efficient Android library designed to manage background file downloads with support for task queuing, resumable downloads, and database-backed progress tracking.

---

## Features

- 🔄 Resumable download support
- 🚀 Multiple parallel downloads
- 📥 Status tracking: Started, In Progress, Completed, Paused, Error
- 📦 Simple integration with custom builder pattern
- 🧩 Clean and modular architecture

---

## Getting Started

### Prerequisites

Ensure your app has the following:

- Android `minSdkVersion` 24+
- Add required permissions in your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

> 📌 Use scoped storage for newer Android versions if required.

### Installation via JitPack

1. Add JitPack to your root `build.gradle`:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency:

```groovy
dependencies {
    implementation 'com.github.muhamadsyafii:KupilDown:1.0.0'
}
```
---

## Usage

### 1. Initialize Downloader

In your `Application` class:

```kotlin
class KupilApp : Application() {
    lateinit var kupilDownloader: KupilDownloader

    override fun onCreate() {
        super.onCreate()
        kupilDownloader = KupilDownloader.create(applicationContext)
    }
}
```

### 2. Create and Enqueue Download Requests

```kotlin
val kupilDownloader = (applicationContext as KupilApp).kupilDownloader

val request = kupilDownloader
    .newRequestBuilder(url = "https://example.com/file.mp4", dirPath = "/Download", fileName = "video.mp4")
    .tag("video-download")
    .build()

val downloadId = kupilDownloader.enqueue(
    request,
    onStart = { /* Update UI */ },
    onProgress = { progress -> /* Update progress bar */ },
    onCompleted = { /* Notify completion */ },
    onPause = { /* Handle pause */ },
    onError = { error -> /* Show error */ }
)
```

### 3. Manage Downloads

```kotlin
kupilDownloader.pause(downloadId)
kupilDownloader.resume(downloadId)
kupilDownloader.cancel(downloadId)
```

You can also cancel by tag or all at once:

```kotlin
kupilDownloader.cancel("video-download")
kupilDownloader.cancelAll()
```

---



## Sample

The sample project includes a UI with 6 files downloading simultaneously using `KupilDownloader`. It demonstrates handling of:

- Start/Cancel toggle
- Pause/Resume
- Status tracking and progress

Simply run the `MainActivity` to test.

```bash
git clone https://github.com/muhamadsyafii/KupilDown.git
cd KupilDown
./gradlew installDebug
```

---

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## License

```
MIT License

Copyright (c) 2025 Muhamad Syafii

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

... [shortened for readability]
```

---

## Credits

Developed by Muhamad Syafii · [GitHub](https://github.com/muhamadsyafii)

---

Feel free to ⭐️ the repo if you find it helpful!
