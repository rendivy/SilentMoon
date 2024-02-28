package com.example.silentmoon.navigation

import androidx.fragment.app.FragmentManager


fun FragmentManager.clearAllBackStack() {
    for (i in 0 until backStackEntryCount) {
        popBackStack()
    }
}