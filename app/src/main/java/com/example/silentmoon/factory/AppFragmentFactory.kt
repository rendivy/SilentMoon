package com.example.silentmoon.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.silentmoon.SleepDetailsFragment
import com.example.silentmoon.SleepPlayerFragment
import com.example.silentmoon.screens.welcome.WelcomeFragment

class AppFragmentFactory(
    private val welcomeFragmentProvider: () -> Fragment,
    private val sleepDetailsFragmentProvider: () -> Fragment,
    private val sleepMusicFragmentProvider: () -> Fragment
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            WelcomeFragment::class.java.name -> welcomeFragmentProvider()
            SleepDetailsFragment::class.java.name -> sleepDetailsFragmentProvider()
            SleepPlayerFragment::class.java.name -> sleepMusicFragmentProvider()
            else -> super.instantiate(classLoader, className)
        }
    }
}
