package com.example.silentmoon.presentation.screens.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ProfileFragmentBinding


class ProfileFragment : Fragment(R.layout.profile_fragment) {

    private lateinit var binding: ProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(inflater, container, false)
        binding.button2.setOnClickListener {
            Toast.makeText(context, "In future update!", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }
}