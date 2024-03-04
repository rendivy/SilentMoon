package com.example.silentmoon.presentation.screens.coursedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.silentmoon.presentation.LightSleepFragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.MaleFragmentBinding
import com.example.silentmoon.presentation.screens.coursedetails.adapter.SoundListAdapter
import com.example.silentmoon.presentation.screens.coursedetails.service.SoundService


class MaleFragment : Fragment(R.layout.male_fragment) {

    private lateinit var binding: MaleFragmentBinding
    private val adapter = SoundListAdapter {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.main_activity_coordinator_layout,
                LightSleepFragment(getString(it))
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MaleFragmentBinding.inflate(inflater, container, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(SoundService.soundList)
        return binding.root
    }
}