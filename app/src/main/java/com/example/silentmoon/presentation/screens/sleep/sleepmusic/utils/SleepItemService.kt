package com.example.silentmoon.presentation.screens.sleep.sleepmusic.utils

import com.example.silentmoon.R
import com.example.silentmoon.presentation.screens.sleep.sleepmusic.item.SleepCategoryItem
import com.example.silentmoon.presentation.screens.sleep.sleepmusic.item.SleepMusicCardItem

data object SleepItemService {

    val categoryList = listOf(
        SleepCategoryItem(
            imageId = R.drawable.all_catergories_icon,
            text = "All"
        ),
        SleepCategoryItem(
            imageId = R.drawable.my_item_icon,
            text = "My"
        ),
        SleepCategoryItem(
            imageId = R.drawable.anxious_item_icon,
            text = "Anxious"
        ),
        SleepCategoryItem(
            imageId = R.drawable.sleep_item_icon,
            text = "Sleep"
        ),
        SleepCategoryItem(
            imageId = R.drawable.kids_item_icon,
            text = "Kids"
        ),
        SleepCategoryItem(
            imageId = R.drawable.happy_item_icon,
            text = "Nature"
        ),
    )

    val relatedList = listOf(
        SleepMusicCardItem(
            imageId = R.drawable.night_island_icon,
            text = "Night Island"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.sweet_sleep_icon,
            text = "Sweet sleep"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.good_sleep_icon,
            text = "Good Night"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.moon_clouds_icon,
            text = "Moon Clouds"
        ),
    )


    val musicCardItemList = listOf(
        SleepMusicCardItem(
            imageId = R.drawable.night_island_icon,
            text = "Night Island"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.sweet_sleep_icon,
            text = "Sweet sleep"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.good_sleep_icon,
            text = "Good Night"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.moon_clouds_icon,
            text = "Moon Clouds"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.sweet_sleep_icon,
            text = "Sweet sleep"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.night_island_icon,
            text = "Night Island"
        ),

        SleepMusicCardItem(
            imageId = R.drawable.moon_clouds_icon,
            text = "Moon Clouds"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.sweet_sleep_icon,
            text = "Sweet sleep"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.good_sleep_icon,
            text = "Good Night"
        ),
        SleepMusicCardItem(
            imageId = R.drawable.good_sleep_icon,
            text = "Good Night"
        ),
    )
}