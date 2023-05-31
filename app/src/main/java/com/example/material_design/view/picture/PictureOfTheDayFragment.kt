package com.example.material_design.view.picture

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.*
import android.view.*
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.core.content.ContextCompat.getColor
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
    lateinit var videoView: VideoView


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
            binding.textView.typeface =
                Typeface.createFromAsset(it.assets, "fonts/AceSansFree-2O2LX.otf")
        }

        binding.imageView.setOnClickListener {
            isExpanded = !isExpanded
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
            R.id.app_bar_bottom_navigation -> activity?.let {
                startActivity(
                    Intent(
                        it,
                        BottomNavigationActivity::class.java
                    )
                )
            }

            R.id.action_favourite -> activity?.let {
                startActivity(
                    Intent(
                        it,
                        ViewPagerActivity::class.java
                    )
                )
            }

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
                if (appState.pictureOfTheDayResponseData.mediaType == "image") {
                    binding.videoView.alpha = 0f
                    binding.imageView.visibility = View.VISIBLE
                    binding.imageView.load(appState.pictureOfTheDayResponseData.url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                        crossfade(true)
                    }
                } else if (appState.pictureOfTheDayResponseData.mediaType == "video") {
                    binding.videoView.alpha = 1f
                    binding.imageView.visibility = View.GONE

                    var videoUrl =
                        "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"

                    videoView = binding.videoView
                    val uri: Uri = Uri.parse(videoUrl)
                    videoView.setVideoURI(uri)
                    val mediaController = MediaController(context)
                    mediaController.setAnchorView(videoView)
                    mediaController.setMediaPlayer(videoView)
                    videoView.setMediaController(mediaController)
                    videoView.start()
                }

                val spannable = SpannableString(appState.pictureOfTheDayResponseData.title)
                binding.titleTextView.setText(spannable, TextView.BufferType.SPANNABLE)
                val spannableText = binding.titleTextView.text as Spannable

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    spannableText.setSpan(
                        context?.let { getColor(it, R.color.my_color_main) }
                            ?.let { BulletSpan(20, it, 30) },
                        0, 6,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableText.setSpan(
                        ForegroundColorSpan(Color.GREEN),
                        0, 8,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableText.setSpan(
                        ForegroundColorSpan(Color.CYAN),
                        8, 16,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableText.setSpan(
                        ForegroundColorSpan(Color.RED),
                        16, spannable.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableText.setSpan(
                        StyleSpan(BOLD),
                        0, spannable.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    spannableText.setSpan(
                        BackgroundColorSpan(Color.GRAY),
                        3, 16,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                }

                val spannableExplanation = SpannableString(appState.pictureOfTheDayResponseData.explanation)
                binding.textView.setText(spannableExplanation, TextView.BufferType.SPANNABLE)
                val spannableTextExplanation = binding.textView.text as Spannable
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    spannableTextExplanation.setSpan(
                        QuoteSpan(Color.BLUE, 10, 20),
                        0, spannableTextExplanation.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
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