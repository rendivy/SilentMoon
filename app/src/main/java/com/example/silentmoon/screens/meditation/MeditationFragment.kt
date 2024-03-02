package com.example.silentmoon.screens.meditation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.MeditationFragmentBinding
import com.example.silentmoon.screens.meditation.adapter.MeditationAdapter
import com.example.silentmoon.screens.meditation.util.MeditationService
import com.example.silentmoon.screens.sleep.sleepmusic.adapter.CategoryAdapter
import com.example.silentmoon.screens.sleep.sleepmusic.utils.SleepItemService


class MeditationFragment : Fragment(R.layout.meditation_fragment) {

    private lateinit var binding: MeditationFragmentBinding
    private val meditationAdapter = MeditationAdapter {}
    private val categoriesAdapter = CategoryAdapter(R.drawable.unselected_meditation_category)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = MeditationFragmentBinding.bind(view)

        binding.meditationRecycleView.apply {
            adapter = meditationAdapter
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            setHasFixedSize(true)
        }
        binding.categoriesRecyclerView.adapter = categoriesAdapter
        categoriesAdapter.submitList(SleepItemService.categoryList)

        meditationAdapter.submitList(MeditationService.meditationItems)
    }


}