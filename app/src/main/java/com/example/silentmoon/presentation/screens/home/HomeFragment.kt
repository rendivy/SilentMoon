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
    private val homeAdapter = HomeAdapter(
        onItemClick = {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    LightSleepFragment(getString(R.string.focus_attention))
                )
                .addToBackStack(null).commit()
        }
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)
        binding.recyclerView.adapter = homeAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        setupButton()
        homeAdapter.submitList(HomeService.homeItemList)
    }


    private fun setupButton() {
        binding.button3.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    LightSleepFragment(getString(R.string.focus_attention))
                )
                .addToBackStack(null).commit()
        }


        binding.button4.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    LightSleepFragment(getString(R.string.focus_attention))
                )
                .addToBackStack(null).commit()
        }
    }
}