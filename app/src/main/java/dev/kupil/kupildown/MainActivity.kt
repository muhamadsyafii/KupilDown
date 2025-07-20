package dev.kupil.kupildown

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dev.kupil.kupildown.databinding.ActivityMainBinding
import dev.kupil.kupildown.utils.Status

class MainActivity : AppCompatActivity() {
  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


  lateinit var dirPath: String
  private var downloadId1 = 0
  private var downloadId2 = 0
  private var downloadId3 = 0
  private var downloadId4 = 0
  private var downloadId5 = 0
  private var downloadId6 = 0

  private lateinit var kupilDownloader: KupilDownloader

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    setupKupilDownloader()
    setupDownload()
    initView()
  }

  private fun setupKupilDownloader() {
    kupilDownloader = (applicationContext as KupilApp).kupilDownloader
    dirPath = Environment.getExternalStorageDirectory().path + "/Download"
  }


  @SuppressLint("SetTextI18n")
  private fun setupDownload() = with(binding) {
    val request1 = kupilDownloader.newRequestBuilder(URL_1, dirPath, FILE_NAME_1).tag(TAG_1).build()
    val request2 = kupilDownloader.newRequestBuilder(URL_2, dirPath, FILE_NAME_2).tag(TAG_2).build()
    val request3 = kupilDownloader.newRequestBuilder(URL_3, dirPath, FILE_NAME_3).tag(TAG_3).build()
    val request4 = kupilDownloader.newRequestBuilder(URL_4, dirPath, FILE_NAME_4).tag(TAG_4).build()
    val request5 = kupilDownloader.newRequestBuilder(URL_5, dirPath, FILE_NAME_5).tag(TAG_5).build()
    val request6 = kupilDownloader.newRequestBuilder(URL_6, dirPath, FILE_NAME_6).tag(TAG_6).build()

    // Request 1
    fileName1.text = FILE_NAME_1
    startCancelButton1.setOnClickListener {
      if (startCancelButton1.text == TEXT_START) {
        downloadId1 = kupilDownloader.enqueue(request1,
          onStart = {
            status1.text = STATUS_STARTED
            startCancelButton1.text = TEXT_CANCEL
            resumePauseButton1.apply {
              isEnabled = true
              visibility = View.VISIBLE
              text = TEXT_PAUSE
            }
          },
          onProgress = {
            status1.text = STATUS_IN_PROGRESS
            progressBar1.progress = it
            progressText1.text = "$it%"
          },
          onCompleted = {
            status1.text = STATUS_COMPLETED
            progressText1.text = "100%"
            startCancelButton1.visibility = View.GONE
            resumePauseButton1.visibility = View.GONE
          },
          onError = {
            status1.text = "$STATUS_ERROR $it"
            resumePauseButton1.visibility = View.GONE
            progressBar1.progress = 0
            progressText1.text = "0%"
          },
          onPause = { status1.text = STATUS_PAUSED }
        )
      } else {
        kupilDownloader.cancel(downloadId1)
        startCancelButton1.text = TEXT_START
      }
    }
    resumePauseButton1.setOnClickListener {
      if (kupilDownloader.status(downloadId1) == Status.PAUSED) {
        resumePauseButton1.text = TEXT_PAUSE
        kupilDownloader.resume(downloadId1)
      } else {
        resumePauseButton1.text = TEXT_RESUME
        kupilDownloader.pause(downloadId1)
      }
    }

    // Request 2
    fileName2.text = FILE_NAME_2
    startCancelButton2.setOnClickListener {
      if (startCancelButton2.text == TEXT_START) {
        downloadId2 = kupilDownloader.enqueue(request2,
          onStart = {
            status2.text = STATUS_STARTED
            startCancelButton2.text = TEXT_CANCEL
            resumePauseButton2.apply {
              isEnabled = true
              visibility = View.VISIBLE
              text = TEXT_PAUSE
            }
          },
          onProgress = {
            status2.text = STATUS_IN_PROGRESS
            progressBar2.progress = it
            progressText2.text = "$it%"
          },
          onCompleted = {
            status2.text = STATUS_COMPLETED
            progressText2.text = "100%"
            startCancelButton2.visibility = View.GONE
            resumePauseButton2.visibility = View.GONE
          },
          onError = {
            status2.text = "$STATUS_ERROR $it"
            resumePauseButton2.visibility = View.GONE
            progressBar2.progress = 0
            progressText2.text = "0%"
          },
          onPause = { status2.text = STATUS_PAUSED }
        )
      } else {
        kupilDownloader.cancel(downloadId2)
        startCancelButton2.text = TEXT_START
      }
    }
    resumePauseButton2.setOnClickListener {
      if (kupilDownloader.status(downloadId2) == Status.PAUSED) {
        resumePauseButton2.text = TEXT_PAUSE
        kupilDownloader.resume(downloadId2)
      } else {
        resumePauseButton2.text = TEXT_RESUME
        kupilDownloader.pause(downloadId2)
      }
    }

    // Request 3
    fileName3.text = FILE_NAME_3
    startCancelButton3.setOnClickListener {
      if (startCancelButton3.text == TEXT_START) {
        downloadId3 = kupilDownloader.enqueue(request3,
          onStart = {
            status3.text = STATUS_STARTED
            startCancelButton3.text = TEXT_CANCEL
            resumePauseButton3.apply {
              isEnabled = true
              visibility = View.VISIBLE
              text = TEXT_PAUSE
            }
          },
          onProgress = {
            status3.text = STATUS_IN_PROGRESS
            progressBar3.progress = it
            progressText3.text = "$it%"
          },
          onCompleted = {
            status3.text = STATUS_COMPLETED
            progressText3.text = "100%"
            startCancelButton3.visibility = View.GONE
            resumePauseButton3.visibility = View.GONE
          },
          onError = {
            status3.text = "$STATUS_ERROR $it"
            resumePauseButton3.visibility = View.GONE
            progressBar3.progress = 0
            progressText3.text = "0%"
          },
          onPause = { status3.text = STATUS_PAUSED }
        )
      } else {
        kupilDownloader.cancel(downloadId3)
        startCancelButton3.text = TEXT_START
      }
    }
    resumePauseButton3.setOnClickListener {
      if (kupilDownloader.status(downloadId3) == Status.PAUSED) {
        resumePauseButton3.text = TEXT_PAUSE
        kupilDownloader.resume(downloadId3)
      } else {
        resumePauseButton3.text = TEXT_RESUME
        kupilDownloader.pause(downloadId3)
      }
    }

    // Request 4
    fileName4.text = FILE_NAME_4
    startCancelButton4.setOnClickListener {
      if (startCancelButton4.text == TEXT_START) {
        downloadId4 = kupilDownloader.enqueue(request4,
          onStart = {
            status4.text = STATUS_STARTED
            startCancelButton4.text = TEXT_CANCEL
            resumePauseButton4.apply {
              isEnabled = true
              visibility = View.VISIBLE
              text = TEXT_PAUSE
            }
          },
          onProgress = {
            status4.text = STATUS_IN_PROGRESS
            progressBar4.progress = it
            progressText4.text = "$it%"
          },
          onCompleted = {
            status4.text = STATUS_COMPLETED
            progressText4.text = "100%"
            startCancelButton4.visibility = View.GONE
            resumePauseButton4.visibility = View.GONE
          },
          onError = {
            status4.text = "$STATUS_ERROR $it"
            resumePauseButton4.visibility = View.GONE
            progressBar4.progress = 0
            progressText4.text = "0%"
          },
          onPause = { status4.text = STATUS_PAUSED }
        )
      } else {
        kupilDownloader.cancel(downloadId4)
        startCancelButton4.text = TEXT_START
      }
    }
    resumePauseButton4.setOnClickListener {
      if (kupilDownloader.status(downloadId4) == Status.PAUSED) {
        resumePauseButton4.text = TEXT_PAUSE
        kupilDownloader.resume(downloadId4)
      } else {
        resumePauseButton4.text = TEXT_RESUME
        kupilDownloader.pause(downloadId4)
      }
    }

    // Request 5
    fileName5.text = FILE_NAME_5
    startCancelButton5.setOnClickListener {
      if (startCancelButton5.text == TEXT_START) {
        downloadId5 = kupilDownloader.enqueue(request5,
          onStart = {
            status5.text = STATUS_STARTED
            startCancelButton5.text = TEXT_CANCEL
            resumePauseButton5.apply {
              isEnabled = true
              visibility = View.VISIBLE
              text = TEXT_PAUSE
            }
          },
          onProgress = {
            status5.text = STATUS_IN_PROGRESS
            progressBar5.progress = it
            progressText5.text = "$it%"
          },
          onCompleted = {
            status5.text = STATUS_COMPLETED
            progressText5.text = "100%"
            startCancelButton5.visibility = View.GONE
            resumePauseButton5.visibility = View.GONE
          },
          onError = {
            status5.text = "$STATUS_ERROR $it"
            resumePauseButton5.visibility = View.GONE
            progressBar5.progress = 0
            progressText5.text = "0%"
          },
          onPause = { status5.text = STATUS_PAUSED }
        )
      } else {
        kupilDownloader.cancel(downloadId5)
        startCancelButton5.text = TEXT_START
      }
    }
    resumePauseButton5.setOnClickListener {
      if (kupilDownloader.status(downloadId5) == Status.PAUSED) {
        resumePauseButton5.text = TEXT_PAUSE
        kupilDownloader.resume(downloadId5)
      } else {
        resumePauseButton5.text = TEXT_RESUME
        kupilDownloader.pause(downloadId5)
      }
    }

    // Request 6
    fileName6.text = FILE_NAME_6
    startCancelButton6.setOnClickListener {
      if (startCancelButton6.text == TEXT_START) {
        downloadId6 = kupilDownloader.enqueue(request6,
          onStart = {
            status6.text = STATUS_STARTED
            startCancelButton6.text = TEXT_CANCEL
            resumePauseButton6.apply {
              isEnabled = true
              visibility = View.VISIBLE
              text = TEXT_PAUSE
            }
          },
          onProgress = {
            status6.text = STATUS_IN_PROGRESS
            progressBar6.progress = it
            progressText6.text = "$it%"
          },
          onCompleted = {
            status6.text = STATUS_COMPLETED
            progressText6.text = "100%"
            startCancelButton6.visibility = View.GONE
            resumePauseButton6.visibility = View.GONE
          },
          onError = {
            status6.text = "$STATUS_ERROR $it"
            resumePauseButton6.visibility = View.GONE
            progressBar6.progress = 0
            progressText6.text = "0%"
          },
          onPause = { status6.text = STATUS_PAUSED }
        )
      } else {
        kupilDownloader.cancel(downloadId6)
        startCancelButton6.text = TEXT_START
      }
    }
    resumePauseButton6.setOnClickListener {
      if (kupilDownloader.status(downloadId6) == Status.PAUSED) {
        resumePauseButton6.text = TEXT_PAUSE
        kupilDownloader.resume(downloadId6)
      } else {
        resumePauseButton6.text = TEXT_RESUME
        kupilDownloader.pause(downloadId6)
      }
    }
  }



  private fun initView() = with(binding) {

  }

  companion object {
    private const val TAG = "MainActivity"

    private const val FILE_NAME_1 = "bunny.mp4"
    private const val URL_1 = "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_30mb.mp4"
    private const val TAG_1 = "$TAG-1"

    private const val FILE_NAME_2 = "100MB.bin"
    private const val URL_2 = "https://speed.hetzner.de/100MB.bin"
    private const val TAG_2 = "$TAG-2"

    private const val FILE_NAME_3 = "docu.pdf"
    private const val URL_3 = "https://file-examples.com/storage/fe3286c49f6458f86eb9ed5/2017/10/file-example_PDF_1MB.pdf"
    private const val TAG_3 = "$TAG-3"

    private const val FILE_NAME_4 = "giphy.gif"
    private const val URL_4 = "https://media.giphy.com/media/Bk0CW5frw4qfS/giphy.gif"
    private const val TAG_4 = "$TAG-4"

    private const val FILE_NAME_5 = "1GB.bin"
    private const val URL_5 = "https://speed.hetzner.de/1GB.bin"
    private const val TAG_5 = "$TAG-5"

    private const val FILE_NAME_6 = "music.mp3"
    private const val URL_6 = "https://file-examples.com/storage/fe3286c49f6458f86eb9ed5/2017/11/file_example_MP3_5MG.mp3"
    private const val TAG_6 = "$TAG-6"


    // Status Texts
    const val STATUS_STARTED = "Started"
    const val STATUS_IN_PROGRESS = "In Progress"
    const val STATUS_COMPLETED = "Completed"
    const val STATUS_PAUSED = "Paused"
    const val STATUS_ERROR = "Error :"

    // Button Texts
    const val TEXT_START = "Start"
    const val TEXT_CANCEL = "Cancel"
    const val TEXT_PAUSE = "Pause"
    const val TEXT_RESUME = "Resume"

  }

}