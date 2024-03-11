package com.example.silentmoon.presentation.screens.welcome

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.WelcomeFragmentBinding
import com.example.silentmoon.presentation.BottomBarVisibility
import com.example.silentmoon.presentation.screens.choosetopic.ChooseTopicFragment
import com.example.silentmoon.presentation.screens.welcome.util.CustomTypefaceSpan

class WelcomeFragment(private val userName: String) : Fragment(R.layout.welcome_fragment) {

    private lateinit var binding: WelcomeFragmentBinding


    override fun onStart() {
        super.onStart()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WelcomeFragmentBinding.inflate(inflater, container, false)
        val fullTextTemplate = getString(R.string.hi_yuriy_welcome_to_silent_moon)
        val fullText = fullTextTemplate.replace("Yuriy", userName)

        val partToSpan = getString(R.string.silent_moon_span)
        val spannableString = SpannableString(fullText)

        val typeface = ResourcesCompat.getFont(requireContext(), R.font.roboto_regular)
        val typefaceSpan = CustomTypefaceSpan(typeface!!)
        val startIndexOfPart = fullText.indexOf(partToSpan)
        val endIndexOfPart = startIndexOfPart + partToSpan.length

        spannableString.setSpan(
            typefaceSpan,
            startIndexOfPart,
            endIndexOfPart,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.singUpButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view,
                    ChooseTopicFragment()
                )
                .addToBackStack(null)
                .commit()
        }
        binding.appLabelTextView.text = spannableString

        return binding.root
    }


}
