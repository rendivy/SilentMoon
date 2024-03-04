package com.example.silentmoon.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.SleepDetailsFragmentBinding
import com.example.silentmoon.presentation.screens.sleep.sleepmusic.adapter.SleepMusicCardAdapter
import com.example.silentmoon.presentation.screens.sleep.sleepmusic.utils.SleepItemService


class SleepDetailsFragment(private val titleImageId: Int, private val titleText: String) :
    Fragment(R.layout.sleep_details_fragment) {

    private lateinit var binding: SleepDetailsFragmentBinding
    private val viewAdapter: SleepMusicCardAdapter = SleepMusicCardAdapter { imageId, text ->
        navigateToSleepDetailsFragment(imageId, text)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SleepDetailsFragmentBinding.inflate(inflater, container, false)


        binding.playButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    SleepPlayerFragment(titleText)
                )
                .addToBackStack(null)
                .commit()
        }

        setupBackButton()
        setupFavouriteButton()
        setupDownloadButton()
        setupRecyclerView()
        setupHeader()
        return binding.root
    }

    private fun navigateToSleepDetailsFragment(imageId: Int, text: String) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_view,
                SleepDetailsFragment(imageId, text)
            )
            .addToBackStack(null)
            .commit()
    }

    private fun setupBackButton() {
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupFavouriteButton() {
        var isFavourite = false
        binding.favouriteButton.setOnClickListener {
            isFavourite = !isFavourite
            val icon =
                if (isFavourite) R.drawable.fill_favourite_icon else R.drawable.favourite_icon
            val message =
                if (isFavourite) R.string.favoured_increased else R.string.favourites_count
            binding.favouriteButton.setImageResource(icon)
            binding.favouritesCount.text = getString(message)
        }
    }

    private fun setupDownloadButton() {
        binding.downloadButton.setOnClickListener {
            val message = R.string.listen_increased
            binding.listenCount.text = getString(message)
            Toast.makeText(this.context, "Downloaded!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        binding.relatedRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.relatedRecyclerView.adapter = viewAdapter
        viewAdapter.submitList(SleepItemService.relatedList)
    }

    private fun setupHeader() {
        binding.headerImage.setImageResource(titleImageId)
        binding.sleepDetailsLabel.text = titleText
    }
}