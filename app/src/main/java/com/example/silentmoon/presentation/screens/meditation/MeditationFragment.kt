package com.example.silentmoon.presentation.screens.meditation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.MeditationFragmentBinding
import com.example.silentmoon.presentation.screens.meditation.adapter.MeditationAdapter
import com.example.silentmoon.presentation.screens.meditation.util.MeditationService
import com.example.silentmoon.presentation.screens.sleep.sleepmusic.adapter.CategoryAdapter
import com.example.silentmoon.presentation.screens.sleep.sleepmusic.utils.SleepItemService


class MeditationFragment : Fragment(R.layout.meditation_fragment) {

    private lateinit var binding: MeditationFragmentBinding
    private val meditationAdapter = MeditationAdapter {}
    private val categoriesAdapter = CategoryAdapter(R.drawable.unselected_meditation_category)

    private companion object {
        const val SPAN_COUNT = 2
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = MeditationFragmentBinding.bind(view)

        binding.meditationRecycleView.apply {
            adapter = meditationAdapter
            layoutManager = StaggeredGridLayoutManager(SPAN_COUNT, LinearLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }
        binding.categoriesRecyclerView.adapter = categoriesAdapter
        categoriesAdapter.submitList(SleepItemService.categoryList)

        meditationAdapter.submitList(MeditationService.meditationItems)
    }


}