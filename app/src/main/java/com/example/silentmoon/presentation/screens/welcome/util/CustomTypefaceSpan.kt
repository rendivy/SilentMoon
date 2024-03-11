package com.example.silentmoon.presentation.screens.welcome.util

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class CustomTypefaceSpan(private val typeface: Typeface) : MetricAffectingSpan() {
    override fun updateMeasureState(p: TextPaint) {
        p.typeface = typeface
    }

    override fun updateDrawState(tp: TextPaint) {
        tp.typeface = typeface
    }
}