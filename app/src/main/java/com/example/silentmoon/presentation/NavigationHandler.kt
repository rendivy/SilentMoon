package com.example.silentmoon.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class NavigationHandler(private val fragmentManager: FragmentManager, private val containerId: Int) {

    fun navigateTo(fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    fun handleNavigationChanges(onFragmentChanged: (Fragment?) -> Unit) {
        fragmentManager.addOnBackStackChangedListener {
            val currentFragment = fragmentManager.findFragmentById(containerId)
            onFragmentChanged(currentFragment)
        }
    }
}