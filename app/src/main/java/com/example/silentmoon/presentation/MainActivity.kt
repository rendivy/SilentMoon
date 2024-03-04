package com.example.silentmoon.presentation

import android.content.res.ColorStateList
import android.graphics.Rect
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivityMainBinding
import com.example.silentmoon.presentation.factory.AppFragmentFactory
import com.example.silentmoon.presentation.screens.home.HomeFragment
import com.example.silentmoon.presentation.screens.home.LightSleepFragment
import com.example.silentmoon.presentation.screens.meditation.MeditationFragment
import com.example.silentmoon.presentation.screens.onboarding.OnBoardingFragment
import com.example.silentmoon.presentation.screens.profile.ProfileFragment
import com.example.silentmoon.presentation.screens.sleep.SleepFragment
import com.example.silentmoon.presentation.screens.sleep.sleepdetails.SleepDetailsFragment
import com.example.silentmoon.presentation.screens.sleep.sleepmusic.SleepMusicFragment
import com.example.silentmoon.presentation.screens.sleep.sleepplayer.SleepPlayerFragment
import com.example.silentmoon.presentation.screens.welcome.WelcomeFragment


class MainActivity : AppCompatActivity(), BottomBarVisibility, UserNameUpdateListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupFragmentFactory()
        setupInitialFragment()
        setupBottomNavigation()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupFragmentFactory() {
        supportFragmentManager.fragmentFactory = AppFragmentFactory(
            welcomeFragmentProvider = { WelcomeFragment("Default User Name") },
            sleepDetailsFragmentProvider = {
                SleepDetailsFragment(
                    R.drawable.night_island_icon,
                    getString(R.string.sleep_music_label)
                )
            },
            sleepMusicFragmentProvider = { SleepPlayerFragment(getString(R.string.night_island_label)) }
            )
        }

    private fun setupInitialFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, OnBoardingFragment())
            .commit()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            handleNavigationItemSelected(item)
        }
    }

    private fun handleNavigationItemSelected(item: MenuItem): Boolean {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
        return when (item.itemId) {
            R.id.navigation_home -> navigateToHomeFragment(currentFragment)
            R.id.navigation_sleep -> navigateToSleepFragment(currentFragment)
            R.id.navigation_meditate -> navigateToMeditateFragment(currentFragment)
            R.id.navigation_music -> navigateToMusicFragment()
            R.id.navigation_profile -> navigateToProfileFragment(currentFragment)
            else -> false
        }
    }

    private fun navigateToHomeFragment(currentFragment: Fragment?): Boolean {
        if (currentFragment !is HomeFragment) {
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
        }
        return true
    }


    private fun navigateToSleepFragment(currentFragment: Fragment?): Boolean {
        if (currentFragment !is SleepFragment && currentFragment !is SleepMusicFragment) {
            binding.bottomNavigation.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.sleepAccent
                )
            )
            val colors =
                ColorStateList.valueOf(ContextCompat.getColor(this, R.color.light_gray))
            binding.bottomNavigation.itemIconTintList = colors
            binding.bottomNavigation.itemTextColor = colors
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, SleepFragment())
                .addToBackStack(null)
                .commit()
        }
        return true
    }


    private fun navigateToMeditateFragment(currentFragment: Fragment?): Boolean {
        if (currentFragment !is MeditationFragment) {
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
        }
        return true
    }


    private fun navigateToMusicFragment(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_view,
                LightSleepFragment(label = getString(R.string.focus_attention))
            )
            .addToBackStack(null)
            .commit()

        return true
    }


    private fun navigateToProfileFragment(currentFragment: Fragment?): Boolean {
        if (currentFragment !is ProfileFragment) {
            binding.bottomNavigation.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.white
                )
            )
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }
        return true
    }


    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            handleTouchEvent(event)
        }
        return super.dispatchTouchEvent(event)
    }

    private fun handleTouchEvent(event: MotionEvent) {
        val v = currentFocus
        if (v is EditText) {
            val outRect = Rect()
            v.getGlobalVisibleRect(outRect)
            if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                v.clearFocus()
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
    }

    override fun setBottomBarVisibility(isVisible: Boolean) {
        binding.bottomNavigation.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun onUserNameUpdate(userName: String) {
        val menu = binding.bottomNavigation.menu
        val profileItem = menu.findItem(R.id.navigation_profile)
        profileItem.title = userName
    }
}