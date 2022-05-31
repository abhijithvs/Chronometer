package com.abhijithvs.chronometer

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    private lateinit var cmTimer: Chronometer
    private lateinit var btnStart: Button
    private lateinit var btnPause: Button

    var isPlay = false
    var pauseOffset : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set find id
        cmTimer = findViewById(R.id.cm_timer)
        btnStart = findViewById(R.id.btn_start)
        btnPause = findViewById(R.id.btn_pause)

        btnStart.setOnClickListener {
            if (!isPlay) {
                cmTimer.base = SystemClock.elapsedRealtime() - pauseOffset
                cmTimer.start()
                btnStart.text = "Stop"
                btnPause.isEnabled = true
                isPlay = true
            }

            else {
                cmTimer.base = SystemClock.elapsedRealtime()
                pauseOffset = 0
                cmTimer.stop()
                btnStart.text = "Start"
                isPlay = false
                btnPause.text = "Pause"
                btnPause.isEnabled = false
            }
        }

        btnPause.setOnClickListener {
            if (isPlay) {
                cmTimer.stop()
                pauseOffset = SystemClock.elapsedRealtime() - cmTimer.base
                isPlay = false
                btnPause.text = "Resume"
            }

            else {
                cmTimer.start()
                cmTimer.base = SystemClock.elapsedRealtime() - pauseOffset
                btnPause.text = "Pause"
                isPlay = true
            }
        }

    }
}