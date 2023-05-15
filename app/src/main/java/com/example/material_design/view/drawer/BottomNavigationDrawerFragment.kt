package com.example.material_design.view.drawer

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.material_design.R
import com.example.material_design.databinding.BottomNavigationLayoutBinding
import com.example.material_design.view.animation.ActivityAnimationsPathTransitions
import com.example.material_design.view.animation.AnimationActivity
import com.example.material_design.view.animation.AnimationActivityEnlarge
import com.example.material_design.view.animation.AnimationActivityFAB
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
            this.dismissAllowingStateLoss()
            when (menuItem.itemId) {
                R.id.navigation_scrolling -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, ScrollingFragment.newInstance())
                        .addToBackStack("")
                        .commit()
                }
                R.id.navigation_swipe -> {
                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, SwipeCardViewFragment.newInstance())
                        .addToBackStack("")
                        .commit()
                }
                R.id.navigation_animation -> {
                    activity?.startActivity(Intent(context, AnimationActivity::class.java))
                }
                R.id.navigation_animations_enlarge -> {
                    activity?.startActivity(Intent(context, AnimationActivityEnlarge::class.java))
                }
                R.id.navigation_animations_path_transitions -> {
                    activity?.startActivity(Intent(context, ActivityAnimationsPathTransitions::class.java))
                }
                R.id.navigation_animations_fab -> {
                    activity?.startActivity(Intent(context, AnimationActivityFAB::class.java))
                }
            }
            false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}