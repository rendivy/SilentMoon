package com.example.silentmoon.presentation

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivityMainBinding
import com.example.silentmoon.presentation.factory.AppFragmentFactory
import com.example.silentmoon.presentation.screens.home.HomeFragment
import com.example.silentmoon.presentation.screens.meditation.MeditationFragment
import com.example.silentmoon.presentation.screens.onboarding.OnBoardingFragment
import com.example.silentmoon.presentation.screens.sleep.welcome.WelcomeSleepFragment
import com.example.silentmoon.presentation.screens.welcome.WelcomeFragment


class MainActivity : AppCompatActivity(), BottomBarVisibility {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.fragmentFactory = AppFragmentFactory(
            welcomeFragmentProvider = {
                WelcomeFragment("Default User Name")
            },
            sleepDetailsFragmentProvider = {
                SleepDetailsFragment(
                    R.drawable.night_island_icon,
                    getString(R.string.sleep_music_label)
                )
            },
            sleepMusicFragmentProvider = {
                SleepPlayerFragment(
                    getString(R.string.night_island_label)
                )
            }
        )

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, OnBoardingFragment())
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    binding.bottomNavigation.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.white
                        )
                    )
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, HomeFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }

                R.id.navigation_sleep -> {
                    binding.bottomNavigation.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.sleepAccent
                        )
                    )
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, WelcomeSleepFragment())
                        .addToBackStack(null)
                        .commit()
                    true

                }

                R.id.navigation_meditate -> {
                    binding.bottomNavigation.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.white
                        )
                    )
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container_view, MeditationFragment())
                        .addToBackStack(null)
                        .commit()
                    true
                }

                R.id.navigation_music -> {
                    binding.bottomNavigation.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            R.color.white
                        )
                    )
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container_view,
                            LightSleepFragment(label = getString(R.string.focus_attention))
                        )
                        .addToBackStack(null)
                        .commit()
                    true
                }


                else -> false
            }
        }

    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun setBottomBarVisibility(isVisible: Boolean) {
        binding.bottomNavigation.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

}