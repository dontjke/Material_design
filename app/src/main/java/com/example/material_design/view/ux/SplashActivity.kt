package com.example.material_design.view.ux

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.material_design.MainActivity
import com.example.material_design.R
import com.example.material_design.view.picture.PictureOfTheDayFragment

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        findViewById<ImageView>(R.id.image_view).animate().rotation(720f).setDuration(2000L).start()

        ObjectAnimator.ofFloat(findViewById<ImageView>(R.id.image_view),View.ROTATION,720f).setDuration(2000L).start()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2000L)
    }
}