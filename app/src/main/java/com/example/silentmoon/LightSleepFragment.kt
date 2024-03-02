package com.example.silentmoon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.silentmoon.databinding.LightSleepFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LightSleepFragment(private val label: String) : Fragment(R.layout.light_sleep_fragment),
    CoroutineScope by MainScope() {


    private lateinit var binding: LightSleepFragmentBinding
    private var isFavourite = false
    private var isMusicPlaying = false

    private val job: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main)

    private companion object {
        const val MUSIC_LENGTH = 2700f
        const val SLIDER_STEP = 1f
        const val SECOND_IN_MINUTES = 60
        const val BUTTON_STEP = 15
        const val TIME_PATTERN = "%02d:%02d"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LightSleepFragmentBinding.inflate(inflater, container, false)
        binding.musicLabel.text = label

        binding.buttonBackground.setOnClickListener {
            isMusicPlaying = !isMusicPlaying
            val playPauseIcon: Int = if (!isMusicPlaying) {
                R.drawable.play_music_icon
            } else {
                R.drawable.pause_icon_label
            }
            if (isMusicPlaying) {
                uiScope.launch(Dispatchers.IO) {
                    while (isMusicPlaying) {
                        withContext(Dispatchers.Main) {
                            if (binding.slider.value < MUSIC_LENGTH) {
                                binding.slider.value += SLIDER_STEP
                            }
                        }
                        delay(1000)
                    }
                }
            } else {
                job.cancel()
            }
            binding.buttonBackground.setImageResource(playPauseIcon)
        }


        setupSlider()
        setupBackButton()
        setupFavouriteButton()
        setupDownloadButton()
        setupBackMusicButton()
        setupForwardMusicButton()

        return binding.root
    }

    private fun setupSlider() {
        binding.slider.valueTo = MUSIC_LENGTH
        binding.slider.stepSize = SLIDER_STEP
        binding.slider.addOnChangeListener { _, value, _ ->
            updateSliderText(value)
        }
    }

    private fun updateSliderText(value: Float) {
        val minutes = value.toInt() / SECOND_IN_MINUTES
        val seconds = value.toInt() % SECOND_IN_MINUTES
        binding.textView12.text = String.format(TIME_PATTERN, minutes, seconds)
    }

    private fun setupFavouriteButton() {
        binding.favouriteButton.setOnClickListener {
            toggleFavourite()
        }
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun toggleFavourite() {
        isFavourite = !isFavourite
        val icon = if (isFavourite) R.drawable.fill_favourite_icon else R.drawable.favourite_icon
        binding.favouriteButton.setImageResource(icon)
    }

    private fun setupDownloadButton() {
        binding.downloadButton.setOnClickListener {
            showDownloadToast()
        }
    }

    private fun showDownloadToast() {
        Toast.makeText(this.context, "Downloaded!", Toast.LENGTH_SHORT).show()
    }

    private fun setupBackMusicButton() {
        binding.backMusicButton.setOnClickListener {
            decreaseSliderValue()
        }
    }

    private fun decreaseSliderValue() {
        val currentValue = binding.slider.value
        if (currentValue - BUTTON_STEP >= 0) {
            binding.slider.value = currentValue - BUTTON_STEP
        } else if (currentValue - BUTTON_STEP < 0) {
            binding.slider.value = 0f
        }
    }

    private fun setupForwardMusicButton() {
        binding.forwardMusicButton.setOnClickListener {
            increaseSliderValue()
        }
    }

    private fun increaseSliderValue() {
        val currentValue = binding.slider.value
        if (currentValue + BUTTON_STEP <= MUSIC_LENGTH) {
            binding.slider.value = currentValue + BUTTON_STEP
        }
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}



