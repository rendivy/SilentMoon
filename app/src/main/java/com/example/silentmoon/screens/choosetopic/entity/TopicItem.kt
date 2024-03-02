package com.example.silentmoon.screens.choosetopic.entity

import com.example.silentmoon.R

data class TopicItem(
    val imageViewId: Int,
    val backgroundColor: Int,
    val textColor: Int = R.color.black,
    val label: String
)
