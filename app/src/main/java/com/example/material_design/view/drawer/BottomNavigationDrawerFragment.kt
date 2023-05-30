package com.example.material_design.view.drawer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.material_design.R
import com.example.material_design.databinding.BottomNavigationLayoutBinding
import com.example.material_design.view.animation.*
import com.example.material_design.view.recycler.RecyclerActivity
import com.example.material_design.view.scrolling.ScrollingFragment
import com.example.material_design.view.swipecard.SwipeCardViewFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {
    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.navigationScrolling -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, ScrollingFragment.newInstance())
                        .addToBackStack("")
                        .commit()
                }
                R.id.navigationSwipe -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, SwipeCardViewFragment.newInstance())
                        .addToBackStack("")
                        .commit()
                }
                R.id.navigationAnimation -> {
                    activity?.startActivity(Intent(context, AnimationActivity::class.java))
                }
                R.id.navigationAnimationsEnlarge -> {
                    activity?.startActivity(Intent(context, AnimationActivityEnlarge::class.java))
                }
                R.id.navigationAnimationsPathTransitions -> {
                    activity?.startActivity(Intent(context, ActivityAnimationsPathTransitions::class.java))
                }
                R.id.navigationAnimationsFab -> {
                    activity?.startActivity(Intent(context, AnimationActivityFAB::class.java))
                }
                R.id.navigationAnimationsBonus -> {
                    activity?.startActivity(Intent(context, AnimationsActivityBonus::class.java))
                }
                R.id.navigationRecyclerView -> {
                    activity?.startActivity(Intent(context, RecyclerActivity::class.java))
                }
            }
            dismiss()
            false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}