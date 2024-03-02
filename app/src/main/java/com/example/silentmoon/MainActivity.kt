package com.example.silentmoon

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.factory.AppFragmentFactory
import com.example.silentmoon.screens.coursedetails.CourseDetailsFragment
import com.example.silentmoon.screens.welcome.WelcomeFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        supportFragmentManager.fragmentFactory = AppFragmentFactory(
            welcomeFragmentProvider = { WelcomeFragment("Default User Name") },
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
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.main_activity_coordinator_layout, CourseDetailsFragment())
                .commit()
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

}