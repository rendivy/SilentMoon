package com.example.silentmoon.screens.sleep

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.WelcomeSleepFragmentBinding


class WelcomeSleepFragment : Fragment(R.layout.welcome_sleep_fragment) {

    private lateinit var binding: WelcomeSleepFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WelcomeSleepFragmentBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}