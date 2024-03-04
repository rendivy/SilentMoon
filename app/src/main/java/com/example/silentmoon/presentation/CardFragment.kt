package com.example.silentmoon.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.MeditationItemBinding
import eightbitlab.com.blurview.RenderEffectBlur

class CardFragment : Fragment(R.layout.meditation_item) {

    private lateinit var binding: MeditationItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MeditationItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decorView = activity?.window?.decorView
        val windowBackground = decorView?.background
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.blurView.clipToOutline = true
        binding.blurView.setupWith(binding.root, RenderEffectBlur())
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(2f)
    }
}