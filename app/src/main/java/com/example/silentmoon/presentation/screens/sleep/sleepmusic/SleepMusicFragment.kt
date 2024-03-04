package com.example.silentmoon.presentation.screens.sleep.sleepmusic


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.SleepMusicFragmentBinding
import com.example.silentmoon.presentation.screens.sleep.sleepdetails.SleepDetailsFragment
import com.example.silentmoon.presentation.screens.sleep.sleepmusic.adapter.SleepMusicCardAdapter
import com.example.silentmoon.presentation.screens.sleep.sleepmusic.utils.SleepItemService

class SleepMusicFragment : Fragment(R.layout.sleep_music_fragment) {

    private lateinit var binding: SleepMusicFragmentBinding

    private val viewAdapter: SleepMusicCardAdapter = SleepMusicCardAdapter { imageId, text ->
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_view,
                SleepDetailsFragment(imageId, text)
            )
            .addToBackStack(null)
            .commit()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SleepMusicFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = viewAdapter
        binding.button.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        viewAdapter.submitList(SleepItemService.musicCardItemList)

        return binding.root

    }

}