package com.example.material_design.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.material_design.databinding.FragmentPictureBinding
import com.example.material_design.viewmodel.PictureOfTheDayViewModel


class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayViewModel::class.java]
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner
        ) { }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}