package com.example.material_design.view.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.material_design.R
import com.example.material_design.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.tabLayout.getTabAt(EARTH)?.setIcon(R.drawable.ic_earth)
        binding.tabLayout.getTabAt(MARS)?.setIcon(R.drawable.ic_mars)
        binding.tabLayout.getTabAt(WEATHER)?.setIcon(R.drawable.ic_system)

    }


    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}