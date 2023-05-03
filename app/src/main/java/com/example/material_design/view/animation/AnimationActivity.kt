package com.example.material_design.view.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.material_design.R
import com.example.material_design.databinding.ActivityAnimationBinding
import com.example.material_design.databinding.ActivityMainBinding
import com.example.material_design.view.picture.PictureOfTheDayFragment

class AnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)


        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}