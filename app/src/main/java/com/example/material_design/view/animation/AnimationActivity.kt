package com.example.material_design.view.animation

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.material_design.R
import com.example.material_design.databinding.ActivityAnimationBinding


class AnimationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerView.adapter = Adapter()
    }

    private fun explode(clickedView: View) {
        val viewRect = Rect()
        clickedView.getGlobalVisibleRect(viewRect)
        val explode = Explode()
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return viewRect
            }
        }
        explode.duration = 2000
        val fade = Fade()
        fade.duration = 2000
        val set = TransitionSet()
            .addTransition(explode)
            .addTransition(fade.addTarget(clickedView))
            .addListener(object : TransitionListenerAdapter() {
                override fun onTransitionEnd(transition: Transition) {
                    transition.removeListener(this)
                    onBackPressed()
                }
            })
        TransitionManager.beginDelayedTransition(binding.recyclerView, set)
        binding.recyclerView.adapter = null
    }

    private inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animation_explode_recycle_view_item,
                    parent,
                    false
                ) as View
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                explode(it)
            }
        }

        override fun getItemCount(): Int {
            return 32
        }
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}