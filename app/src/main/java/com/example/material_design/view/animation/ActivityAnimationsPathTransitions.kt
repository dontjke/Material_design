package com.example.material_design.view.animation

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.material_design.databinding.ActivityAnimationsPathTransitionsBinding


class ActivityAnimationsPathTransitions : AppCompatActivity() {
    private lateinit var binding: ActivityAnimationsPathTransitionsBinding
    private var toRightAnimation = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsPathTransitionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val changeBounds = ChangeBounds()
            val arcMotion = ArcMotion()
            arcMotion.minimumHorizontalAngle = 90f
            arcMotion.minimumVerticalAngle = 90f
            changeBounds.setPathMotion(arcMotion)
            changeBounds.duration = 2000
            TransitionManager.beginDelayedTransition(
                binding.transitionsContainer,
                changeBounds
            )
            toRightAnimation =! toRightAnimation
            val params = binding.button.layoutParams as FrameLayout.LayoutParams
            params.gravity =
                if (toRightAnimation) {
                    Gravity.END or Gravity.BOTTOM
                } else {
                    Gravity.START or Gravity.TOP
                }
                    binding.button.layoutParams = params
        }
    }
}
