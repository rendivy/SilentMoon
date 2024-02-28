package com.example.silentmoon.screens.sleep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.SleepDetailsFragment
import com.example.silentmoon.databinding.SleepFragmentBinding
import com.example.silentmoon.screens.sleep.sleepmusic.SleepMusicFragment
import com.example.silentmoon.screens.sleep.sleepmusic.adapter.CategoryAdapter
import com.example.silentmoon.screens.sleep.sleepmusic.adapter.SleepMusicCardAdapter
import com.example.silentmoon.screens.sleep.sleepmusic.utils.SleepItemService

class SleepFragment : Fragment(R.layout.sleep_fragment) {

    private lateinit var binding: SleepFragmentBinding

    private val viewAdapter: SleepMusicCardAdapter = SleepMusicCardAdapter { imageId, text ->
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.main_activity_coordinator_layout,
                SleepDetailsFragment(imageId, text)
            )
            .addToBackStack(null)
            .commit()

    }
    private val categoryAdapter: CategoryAdapter = CategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SleepFragmentBinding.inflate(inflater, container, false)
        binding.categoriesRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoriesRecyclerView.adapter = categoryAdapter
        binding.oceanMoonStartButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.main_activity_coordinator_layout,
                    SleepMusicFragment()
                )
                .addToBackStack(null)
                .commit()
        }
        categoryAdapter.submitList(SleepItemService.categoryList)
        binding.sleepRecyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.sleepRecyclerView.adapter = viewAdapter
        binding.sleepRecyclerView.setHasFixedSize(true);
        binding.sleepRecyclerView.isNestedScrollingEnabled = false;
        viewAdapter.submitList(SleepItemService.musicCardItemList)
        return binding.root
    }
}