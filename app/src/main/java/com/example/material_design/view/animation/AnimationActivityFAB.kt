package com.example.material_design.view.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.material_design.R
import com.example.material_design.databinding.ActivityAnimationsFabBinding

private const val rotation = "rotation"
private const val translationY = "translationY"
private const val rotationFrom = 0f
private const val rotationTo = 225f
private const val translationOneContainer = -130f
private const val translationTwoContainer = -250f
private const val translationPlusImageFrom = 0f
private const val translationPlusImageTo = -180f
private const val translationZero = 0f
private const val duration = 500L

class AnimationActivityFAB : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsFabBinding
    private var isExpanded = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsFabBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFAB()

    }
    private fun setFAB() {
        setInitialState()
        binding.fab.setOnClickListener {
            if (isExpanded) {
                collapseFab()
            } else {
                expandFAB()
            }
        }

    }
    private fun setInitialState() {
        binding.transparentBackground.apply {
            alpha = 0f
        }
        binding.optionTwoContainer.apply {
            alpha = 0f
            isClickable = false
        }
        binding.optionOneContainer.apply {
            alpha = 0f
            isClickable = false
        }
    }
    private fun expandFAB() {
        isExpanded = true
        ObjectAnimator.ofFloat(binding.plusImageview, rotation, rotationFrom,
            rotationTo).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, translationY,
            translationTwoContainer).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, translationY,
            translationOneContainer).start()
        binding.optionTwoContainer.animate()
            .alpha(1f)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = true
                    binding.optionTwoContainer.setOnClickListener {
                        Toast.makeText(this@AnimationActivityFAB, R.string.option_2,
                            Toast.LENGTH_SHORT).show()
                    }
                }
            })
        binding.optionOneContainer.animate()
            .alpha(1f)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = true
                    binding.optionOneContainer.setOnClickListener {
                        Toast.makeText(this@AnimationActivityFAB, R.string.option_1,
                            Toast.LENGTH_SHORT).show()
                    }
                }
            })
        binding.transparentBackground.animate()
            .alpha(0.9f)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = true
                }
            })
    }

    private fun collapseFab() {
        isExpanded = false
        ObjectAnimator.ofFloat(binding.plusImageview, rotation,
            translationPlusImageFrom, translationPlusImageTo).start()
        ObjectAnimator.ofFloat(binding.optionTwoContainer, translationY,
            translationZero).start()
        ObjectAnimator.ofFloat(binding.optionOneContainer, translationY,
            translationZero).start()
        binding.optionTwoContainer.animate()
            .alpha(0f)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionTwoContainer.isClickable = false
                    binding.optionOneContainer.setOnClickListener(null)
                }
            })
        binding.optionOneContainer.animate()
            .alpha(0f)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.optionOneContainer.isClickable = false
                }
            })
        binding.transparentBackground.animate()
            .alpha(0f)
            .setDuration(duration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    binding.transparentBackground.isClickable = false
                }
            })
    }
}