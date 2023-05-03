package com.example.material_design.view.animation

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.material_design.R
import com.example.material_design.databinding.ActivityAnimationBinding
import com.example.material_design.databinding.ActivityMainBinding
import com.example.material_design.view.picture.PictureOfTheDayFragment

class AnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding
    private var textIsVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)


        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.transitionsContainer)
            textIsVisible =! textIsVisible
            binding.text.visibility = if (textIsVisible) View.VISIBLE else View.GONE
        }

    }
}