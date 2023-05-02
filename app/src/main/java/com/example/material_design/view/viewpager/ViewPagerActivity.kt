package com.example.material_design.view.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.example.material_design.R
import com.example.material_design.databinding.ActivityViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)
        setTabs()
    }

    private fun setTabs() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                EARTH -> {
                    getString(R.string.earth)
                }
                MARS -> {
                    getString(R.string.mars)
                }
                WEATHER -> {
                    getString(R.string.weather)
                }
                else -> {
                    getString(R.string.earth)
                }
            }
            tab.icon = when (position) {
                EARTH -> {
                    ContextCompat.getDrawable(this@ViewPagerActivity, R.drawable.ic_earth)
                }
                MARS -> {
                    ContextCompat.getDrawable(this@ViewPagerActivity, R.drawable.ic_mars)
                }
                WEATHER -> {
                    ContextCompat.getDrawable(this@ViewPagerActivity, R.drawable.ic_system)
                }
                else -> {
                    ContextCompat.getDrawable(this@ViewPagerActivity, R.drawable.ic_earth)
                }
            }
        }.attach()
    }

    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}