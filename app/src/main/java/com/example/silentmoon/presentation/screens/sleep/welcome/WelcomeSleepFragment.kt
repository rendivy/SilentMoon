package com.example.silentmoon.presentation.screens.sleep.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.WelcomeSleepFragmentBinding
import com.example.silentmoon.presentation.BottomBarVisibility
import com.example.silentmoon.presentation.screens.sleep.SleepFragment


class WelcomeSleepFragment : Fragment(R.layout.welcome_sleep_fragment) {

    private lateinit var binding: WelcomeSleepFragmentBinding


    override fun onStart() {
        super.onStart()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WelcomeSleepFragmentBinding.inflate(inflater, container, false)

        binding.getStartedButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, SleepFragment())
                .commit()
        }


        return binding.root
    }
}