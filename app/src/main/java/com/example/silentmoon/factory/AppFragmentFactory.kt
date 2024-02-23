package com.example.silentmoon.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.silentmoon.WelcomeFragment

class AppFragmentFactory(private val fragmentProvider: () -> Fragment) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            WelcomeFragment::class.java.name -> fragmentProvider()
            else -> super.instantiate(classLoader, className)
        }
    }
}