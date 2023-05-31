package com.example.material_design.view.picture

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.example.material_design.MainActivity
import com.example.material_design.R
import com.example.material_design.databinding.FragmentPictureStartBinding
import com.example.material_design.utils.BASE_URL_WIKI
import com.example.material_design.view.drawer.BottomNavigationDrawerFragment
import com.example.material_design.view.scrolling.settings.SettingsFragment
import com.example.material_design.view.viewpager.BottomNavigationActivity
import com.example.material_design.view.viewpager.ViewPagerActivity
import com.example.material_design.viewmodel.AppState
import com.example.material_design.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*


class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureStartBinding? = null
    private val binding get() = _binding!!
    private var isExpanded = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfTheDayViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            binding.textView.typeface = Typeface.createFromAsset(it.assets, "Pelicana-PK62P.otf")
        }

        binding.imageView.setOnClickListener {
            isExpanded =! isExpanded
            TransitionManager.beginDelayedTransition(
                binding.root, TransitionSet()
                    .addTransition(ChangeBounds())
                    .addTransition(ChangeImageTransform())
            )
            val params: ViewGroup.LayoutParams = binding.imageView.layoutParams
            params.height =
                if (isExpanded) ViewGroup.LayoutParams.MATCH_PARENT else
                    ViewGroup.LayoutParams.WRAP_CONTENT
            binding.imageView.layoutParams = params
            binding.imageView.scaleType =
                if (isExpanded) ImageView.ScaleType.CENTER_CROP else
                    ImageView.ScaleType.FIT_CENTER
        }


        viewModel.getLiveData().observe(viewLifecycleOwner) { appState ->
            renderData(appState)
        }

        binding.todayChip.setOnClickListener {
            viewModel.sendRequestForPicture(currentDateWithDayOffset(0))
        }
        binding.yesterdayChip.setOnClickListener {
            viewModel.sendRequestForPicture(currentDateWithDayOffset(-1))
        }
        binding.dayBeforeYesterdayChip.setOnClickListener {
            viewModel.sendRequestForPicture(currentDateWithDayOffset(-2))
        }

        binding.textInput.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("$BASE_URL_WIKI${binding.input.text.toString()}")
            })
        }
        binding.todayChip.performClick()

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    private fun currentDateWithDayOffset(offset: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + offset)
        return calendar.time
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_bottom_navigation -> activity?.let { startActivity(Intent(it,
                BottomNavigationActivity::class.java)) }

            R.id.action_favourite -> activity?.let { startActivity(Intent(it, ViewPagerActivity::class.java)) }

            R.id.action_settings -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .hide(this)
                    .add(R.id.container, SettingsFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }


        }
        return super.onOptionsItemSelected(item)
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
                val text = appState.pictureOfTheDayResponseData.explanation
                if (text.isEmpty()) {
                    Snackbar.make(binding.root, R.string.explanation_empty, Snackbar.LENGTH_LONG).show()
                } else {
                    binding.textView.text = text
                }
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