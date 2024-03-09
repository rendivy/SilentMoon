package com.example.silentmoon.presentation

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.NumberPicker
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.TimepickerFragmentBinding
import com.example.silentmoon.presentation.navigation.clearAllBackStack
import com.example.silentmoon.presentation.screens.home.HomeFragment
import com.ozcanalasalvar.datepicker.view.timepicker.TimePicker

class TimePickerFragment : Fragment(R.layout.test) {

    private lateinit var binding: TimepickerFragmentBinding


    override fun onStart() {
        super.onStart()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(false)
    }

    override fun onStop() {
        super.onStop()
        (activity as? BottomBarVisibility)?.setBottomBarVisibility(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TimepickerFragmentBinding.inflate(inflater, container, false)
        binding.singUpButton.setOnClickListener {
            parentFragmentManager.clearAllBackStack()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, HomeFragment())
                .commit()
        }
        binding.noThanksTextView.setOnClickListener {
            parentFragmentManager.clearAllBackStack()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, HomeFragment())
                .commit()
        }
        return binding.root
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
class CustomTimePicker(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val hourPicker: NumberPicker
    private val minutePicker: NumberPicker
    private val amPmPicker: NumberPicker


    override fun addViewInLayout(
        child: View?,
        index: Int,
        params: ViewGroup.LayoutParams?
    ): Boolean {
        val result = super.addViewInLayout(child, index, params)
        if (child is EditText) {
            child.textSize = 24f
        }
        return result
    }

    init {

        orientation = HORIZONTAL
        hourPicker = NumberPicker(context).apply {
            minValue = 0
            maxValue = 12
        }
        minutePicker = NumberPicker(context).apply {
            minValue = 0
            maxValue = 59
        }
        amPmPicker = NumberPicker(context).apply {
            minValue = 0
            maxValue = 1
            displayedValues = arrayOf("AM", "PM")
        }
        addView(hourPicker)
        addView(minutePicker)
        addView(amPmPicker)
    }


    private fun setNumberPickerTextSize(numberPicker: NumberPicker, size: Float) {
        val count = numberPicker.childCount
        for (i in 0 until count) {
            val child = numberPicker.getChildAt(i)
            if (child is EditText) {
                try {
                    child.textSize = size
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


}


@RequiresApi(Build.VERSION_CODES.Q)
class CustomNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {


    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.x.toInt()
        val y = ev.y.toInt()

        val child = findChildViewUnder(x, y)
        if (child is TimePicker) {
            return false
        }

        return super.onInterceptTouchEvent(ev)
    }

    private fun findChildViewUnder(x: Int, y: Int): View? {
        val location = IntArray(2)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.getLocationOnScreen(location)
            val rect = Rect(
                location[0],
                location[1],
                location[0] + child.width,
                location[1] + child.height
            )
            if (rect.contains(x, y)) {
                return child
            }
        }
        return null
    }
}