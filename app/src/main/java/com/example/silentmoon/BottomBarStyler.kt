package com.example.silentmoon

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.silentmoon.presentation.screens.home.HomeFragment
import com.example.silentmoon.presentation.screens.meditation.MeditationFragment
import com.example.silentmoon.presentation.screens.profile.ProfileFragment
import com.example.silentmoon.presentation.screens.sleep.SleepFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomBarStyler(private val bottomNavigation: BottomNavigationView, private val context: Context) {

    fun styleFor(fragment: Fragment?) {
        when (fragment) {
            is HomeFragment, is MeditationFragment, is ProfileFragment -> {
                setColors(R.color.white, R.color.bottom_nav_icon_color, R.color.bottom_nav_text_color)
            }
            is SleepFragment -> {
                setColors(R.color.sleepAccent, R.color.bottom_nav_icon_color, R.color.bottom_nav_icon_color)
            }
        }
    }

    private fun setColors(backgroundColor: Int, iconColor: Int, textColor: Int) {
        bottomNavigation.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
        val colors = context.resources.getColorStateList(iconColor, null)
        val textColors = context.resources.getColorStateList(textColor, null)
        bottomNavigation.itemIconTintList = colors
        bottomNavigation.itemTextColor = textColors
    }
}