package com.example.material_design.view.viewpager

import android.os.Bundle
import android.view.LayoutInflater
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

        setCustomTabs()
    }

    private fun setCustomTabs() {
        val layoutInflater = LayoutInflater.from(this)
        with(binding.tabLayout) {
            getTabAt(EARTH)?.customView =
                layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_earth, null)
            getTabAt(MARS)?.customView =
                layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_mars, null)
            getTabAt(WEATHER)?.customView =
                layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_weather, null)
        }

    }


    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}