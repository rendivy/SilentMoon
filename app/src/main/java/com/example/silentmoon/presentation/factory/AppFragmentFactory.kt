package com.example.silentmoon.presentation.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.silentmoon.presentation.LightSleepFragment
import com.example.silentmoon.presentation.SleepDetailsFragment
import com.example.silentmoon.presentation.SleepPlayerFragment
import com.example.silentmoon.presentation.screens.welcome.WelcomeFragment

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
            LightSleepFragment::class.java.name -> LightSleepFragment("Default User Name")
            else -> super.instantiate(classLoader, className)
        }
    }
}
