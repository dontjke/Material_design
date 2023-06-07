package com.example.material_design.view.scrolling.settings

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.material_design.R
import com.example.material_design.databinding.FragmentSettingsBinding
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun show() {
        GuideView.Builder(requireContext())
            .setTitle("Guide Title Text")
            .setContentText("Guide Description Text\n ...Guide Description Text\n ...Guide Description Text")
            .setTargetView(binding.materialButton)
            .setDismissType(DismissType.anywhere)
            .build()
            .show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded)
                show()
        }, 500)


        binding.redChip.setOnClickListener {
            context?.setTheme(R.style.MyPurpleTheme)
        }
        binding.purpleChip.setOnClickListener {

        }
        binding.greenChip.setOnClickListener {
        }
    }


    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}