package com.example.silentmoon.screens.coursedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.silentmoon.R
import com.example.silentmoon.databinding.CourseDetailsFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseDetailsFragment : Fragment(R.layout.course_details_fragment) {


    private lateinit var binding: CourseDetailsFragmentBinding

    private companion object {
        const val TAB_COUNT = 2
        const val MALE_TAB_INDEX = 0
        const val FEMALE_TAB_INDEX = 1

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = CourseDetailsFragmentBinding.inflate(inflater, container, false)
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val tabsArray = arrayOf(
            "MALE VOICE",
            "FEMALE VOICE"
        )


        lifecycleScope.launch(Dispatchers.Main) {
            val adapter = ViewPagerAdapter(parentFragmentManager, lifecycle)
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabsArray[position]
            }.attach()
        }

        return binding.root
    }

    class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int {
            return TAB_COUNT
        }

        override fun createFragment(position: Int): Fragment {
            when (position) {
                MALE_TAB_INDEX -> return MaleFragment()
                FEMALE_TAB_INDEX -> return FemaleFragment()
            }
            return MaleFragment()
        }
    }

}