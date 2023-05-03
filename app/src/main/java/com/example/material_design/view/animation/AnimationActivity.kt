package com.example.material_design.view.animation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.transition.*
import com.example.material_design.databinding.ActivityAnimationBinding

class AnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding
    private var textIsVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)


        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {

            val myAutoTransition = TransitionSet()
            //myAutoTransition.ordering = TransitionSet.ORDERING_TOGETHER
            myAutoTransition.ordering = TransitionSet.ORDERING_SEQUENTIAL
            val fade = Slide()
            fade.duration = 1000L
            val changeBounds = ChangeBounds()
            changeBounds.duration = 1000L
            myAutoTransition.addTransition(fade)
            myAutoTransition.addTransition(changeBounds)
            TransitionManager.beginDelayedTransition(binding.transitionsContainer,myAutoTransition)
            textIsVisible = !textIsVisible
            binding.text.visibility = if (textIsVisible) View.VISIBLE else View.GONE
        }

    }
}