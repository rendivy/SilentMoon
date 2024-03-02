package com.example.silentmoon.screens.coursedetails.service

import com.example.silentmoon.R
import com.example.silentmoon.screens.coursedetails.entity.SoundItem

object SoundService {
    val soundList = listOf(
        SoundItem(
            labelId = R.string.focus_attention,
            soundTimeId = R.string.course_item_duration
        ),
        SoundItem(
            labelId = R.string.body_scan,
            soundTimeId = R.string.body_scan_duration
        ),
        SoundItem(
            labelId = R.string.make_happiness,
            soundTimeId = R.string.make_happiness_duration
        ),
        SoundItem(
            labelId = R.string.self_compassion,
            soundTimeId = R.string.self_compassion_duration
        ),
        SoundItem(
            labelId = R.string.mindful_breathing,
            soundTimeId = R.string.mindful_breathing_duration
        ),
        SoundItem(
            labelId = R.string.loving_kindness,
            soundTimeId = R.string.loving_kindness_duration
        ),
    )
}