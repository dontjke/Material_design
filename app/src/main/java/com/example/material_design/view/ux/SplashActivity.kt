package com.example.material_design.view.ux

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.material_design.MainActivity
import com.example.material_design.R
import com.google.android.material.progressindicator.LinearProgressIndicator

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val progress = findViewById<LinearProgressIndicator>(R.id.progress)
        val fullTime = 2000f
        object : CountDownTimer(fullTime.toLong(), 1L) {
            override fun onTick(millisUntilFinished: Long) {
                val process = ((1 - millisUntilFinished / fullTime) * 100).toInt()
                if (progress.progress != process)
                    progress.progress = process
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

        }.start()
    }
}