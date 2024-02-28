package com.example.silentmoon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.silentmoon.databinding.MusicFragmentBinding


class SleepPlayerFragment(private val label: String) : Fragment(R.layout.music_fragment) {

    private lateinit var binding: MusicFragmentBinding
    private var isFavourite = false

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
        binding = MusicFragmentBinding.inflate(inflater, container, false)
        binding.musicLabel.text = label
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
}