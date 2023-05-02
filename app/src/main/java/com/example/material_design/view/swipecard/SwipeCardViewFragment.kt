package com.example.material_design.view.swipecard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.example.material_design.R
import com.example.material_design.databinding.FragmentCardViewBinding
import com.google.android.material.behavior.SwipeDismissBehavior


class SwipeCardViewFragment : Fragment() {
    private var _binding: FragmentCardViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mCardView: View = binding.swipeCardView
        val swipe: SwipeDismissBehavior<CardView?> = SwipeDismissBehavior<CardView?>()
        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
        swipe.listener = object : SwipeDismissBehavior.OnDismissListener {
            override fun onDismiss(view: View?) {
                Toast.makeText(context, getString(R.string.card_swiped), Toast.LENGTH_SHORT).show()
            }

            override fun onDragStateChanged(state: Int) {}
        }

        val coordinatorParams: CoordinatorLayout.LayoutParams =
            mCardView.layoutParams as CoordinatorLayout.LayoutParams
        coordinatorParams.behavior = swipe

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = SwipeCardViewFragment()
    }
}