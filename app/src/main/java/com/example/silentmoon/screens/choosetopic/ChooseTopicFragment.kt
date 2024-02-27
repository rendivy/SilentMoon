package com.example.silentmoon.screens.choosetopic

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ChooseTopicFragmentBinding
import com.example.silentmoon.screens.choosetopic.adapter.TopicAdapter
import com.example.silentmoon.screens.choosetopic.adapter.TopicService
import com.example.silentmoon.screens.choosetopic.util.SpaceItemDecorator
import com.example.silentmoon.screens.onboarding.OnBoardingFragment
import com.example.silentmoon.screens.welcome.util.CustomTypefaceSpan


class ChooseTopicFragment : Fragment(R.layout.choose_topic_fragment) {

    private lateinit var binding: ChooseTopicFragmentBinding

    private var viewAdapter: TopicAdapter = TopicAdapter {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_activity_coordinator_layout, OnBoardingFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val fullText = getString(R.string.what_brings_you_to_silent_moon)

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



        binding = ChooseTopicFragmentBinding.inflate(inflater, container, false)

        binding.textView7.text = spannableString
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.adapter = viewAdapter
        binding.recyclerView.addItemDecoration(SpaceItemDecorator())
        viewAdapter.submitList(TopicService.topics)

        return binding.root

    }
}



