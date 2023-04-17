package com.example.material_design.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.material_design.R
import com.example.material_design.databinding.FragmentPictureBinding
import com.example.material_design.viewmodel.AppState
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

    private val viewModel : PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayViewModel::class.java]
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner ) { appState->
            renderData(appState)
        }
        viewModel.sendRequest()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {

            }
            AppState.Loading -> {

            }
            is AppState.Success -> {
                binding.imageView.load(appState.pictureOfTheDayResponseData.url) {
                    /*lifecycle(this@PictureOfTheDayFragment)
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.ic_no_photo_vector)
                    crossfade(true)*/
                }
            }
        }
    }
companion object{
    fun newInstance()= PictureOfTheDayFragment()
}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}