package com.example.silentmoon.presentation.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.HomeFragmentBinding
import com.example.silentmoon.presentation.screens.home.adapter.HomeAdapter
import com.example.silentmoon.presentation.screens.home.service.HomeService


class HomeFragment : Fragment(R.layout.home_fragment) {

    private lateinit var binding: HomeFragmentBinding
    private val homeAdapter = HomeAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)
        binding.recyclerView.adapter = homeAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeAdapter.submitList(HomeService.homeItemList)

    }
}