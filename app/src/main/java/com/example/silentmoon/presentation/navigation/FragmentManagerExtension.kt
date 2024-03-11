package com.example.silentmoon.presentation.navigation

import androidx.fragment.app.FragmentManager


fun FragmentManager.clearAllBackStack() {
    for (i in 0 until backStackEntryCount) {
        popBackStack()
    }
}