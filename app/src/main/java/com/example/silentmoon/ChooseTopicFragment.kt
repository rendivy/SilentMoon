package com.example.silentmoon

import android.graphics.Rect
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.silentmoon.adapter.TopicAdapter
import com.example.silentmoon.adapter.TopicService
import com.example.silentmoon.databinding.ChooseTopicFragmentBinding


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




class SpaceItemDecorator : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val layoutParams = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex

        if (spanIndex == 0) {
            val marginParentEnd = parent.marginEnd
            layoutParams.marginEnd = marginParentEnd / 2
        }
        else {
            val marginParentStart = parent.marginStart
            layoutParams.marginStart = marginParentStart / 2
        }

        view.layoutParams = layoutParams
    }
}