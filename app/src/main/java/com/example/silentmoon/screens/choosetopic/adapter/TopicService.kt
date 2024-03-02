package com.example.silentmoon.screens.choosetopic.adapter

import com.example.silentmoon.R
import com.example.silentmoon.screens.choosetopic.entity.TopicItem

object TopicService {

    val topics = listOf(
        TopicItem(
            R.drawable.reduce_stress_icon,
            label = "Reduce Stress",
            textColor = R.color.welcome_label_color,
            backgroundColor = R.color.purpura_blue
        ),
        TopicItem(
            R.drawable.improve_perfomance_icon,
            label = "Improve Performance",
            textColor = R.color.white,
            backgroundColor = R.color.orange_card_background
        ),
        TopicItem(
            R.drawable.reduce_anxiety_icon,
            label = "Reduce Anxiety",
            backgroundColor = R.color.yellow_card_background
        ),
        TopicItem(
            R.drawable.increase_happines_icon,
            label = "Increase Happiness",
            backgroundColor = R.color.orange_light_card_background
        ),
        TopicItem(
            R.drawable.personal_grow_icon,
            label = "Personal Growth",
            textColor = R.color.welcome_label_color,
            backgroundColor = R.color.green_card_background
        ),
        TopicItem(
            R.drawable.better_sleep_icon,
            label = "Better Sleep",
            textColor = R.color.white,
            backgroundColor = R.color.black_card_background
        ),

        TopicItem(
            R.drawable.reduce_stress_icon,
            label = "Reduce Anxiety",
            backgroundColor = R.color.reduce_anxiety_card_background
        ),
        TopicItem(
            R.drawable.improve_focus_label,
            label = "Improve Focus",
            textColor = R.color.improve_card_font_color,
            backgroundColor = R.color.improve_card_background
        ),
    )
}