package com.example.material_design.view.animation

import android.os.Bundle
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.material_design.R
import com.example.material_design.databinding.ActivityAnimationsBonusStartBinding


class AnimationsActivityBonus : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBonusStartBinding
    private var show = false
    private val constraintSet = ConstraintSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBonusStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backgroundImage.setOnClickListener { if (show) hideComponents() else
            showComponents() }
    }
    private fun showComponents() {
        show = true
        constraintSet.clone(this, R.layout.activity_animations_bonus_end)
        animateComponents()
    }

    private fun hideComponents() {
        show = false
        constraintSet.clone(this, R.layout.activity_animations_bonus_start)
        animateComponents()
    }

    private fun animateComponents(){
        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(5.0f)
        transition.duration = 1200
        TransitionManager.beginDelayedTransition(binding.constraintContainer,
            transition)
        constraintSet.applyTo(binding.constraintContainer)
    }
}
