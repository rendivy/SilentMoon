package com.example.silentmoon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.silentmoon.databinding.SleepDetailsFragmentBinding
import com.example.silentmoon.screens.sleep.sleepmusic.adapter.SleepMusicCardAdapter
import com.example.silentmoon.screens.sleep.sleepmusic.utils.SleepItemService


class SleepDetailsFragment(private val titleImageId: Int, private val titleText: String) :
    Fragment(R.layout.sleep_details_fragment) {

    private lateinit var binding: SleepDetailsFragmentBinding
    private val viewAdapter: SleepMusicCardAdapter = SleepMusicCardAdapter { imageId, text ->
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.main_activity_coordinator_layout,
                SleepDetailsFragment(imageId, text)
            )
            .addToBackStack(null)
            .commit()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var isFavourite = false

        binding = SleepDetailsFragmentBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.favouriteButton.setOnClickListener {
            isFavourite = !isFavourite
            val icon = if (isFavourite) R.drawable.fill_favourite_icon else R.drawable.favourite_icon
            val message = if (isFavourite) R.string.favoured_increased else R.string.favourites_count
            binding.favouriteButton.setImageResource(icon)
            binding.favouritesCount.text = getString(message)
        }
        binding.downloadButton.setOnClickListener {
            val message = R.string.listen_increased
            binding.listenCount.text = getString(message)
            Toast.makeText(this.context, "Downloaded!", Toast.LENGTH_SHORT).show()
        }
        binding.relatedRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.relatedRecyclerView.adapter = viewAdapter
        binding.headerImage.setImageResource(titleImageId)
        binding.sleepDetailsLabel.text = titleText
        viewAdapter.submitList(SleepItemService.musicCardItemList)
        return binding.root
    }

}