package com.example.material_design.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.material_design.R
import com.example.material_design.databinding.FragmentPictureBinding
import com.example.material_design.utils.BASE_URL_WIKI
import com.example.material_design.viewmodel.AppState
import com.example.material_design.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.snackbar.Snackbar


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
        viewModel.getLiveData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }
        viewModel.sendRequest()

        binding.todayChip.setOnClickListener {
            Toast.makeText(requireContext(),R.string.today,Toast.LENGTH_SHORT).show()
        }
        binding.yesterdayChip.setOnClickListener {
            Toast.makeText(requireContext(),R.string.yesterday,Toast.LENGTH_SHORT).show()
        }
        binding.dayBeforeYesterdayChip.setOnClickListener {
            Toast.makeText(requireContext(),R.string.day_before_yesterday,Toast.LENGTH_SHORT).show()
        }

        binding.textInput.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("$BASE_URL_WIKI${binding.input.text.toString()}")
            })
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loadingProgressBar.visibility = View.GONE
                Snackbar.make(binding.root, R.string.something_wrong, Snackbar.LENGTH_LONG).show()
            }
            AppState.Loading -> {
                binding.loadingProgressBar.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingProgressBar.visibility = View.GONE
                binding.imageView.load(appState.pictureOfTheDayResponseData.url) {
                    lifecycle(this@PictureOfTheDayFragment)
                    error(R.drawable.ic_load_error_vector)
                    placeholder(R.drawable.ic_no_photo_vector)
                    crossfade(true)
                }
                binding.titleTextView.text = appState.pictureOfTheDayResponseData.title

            }
        }
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}