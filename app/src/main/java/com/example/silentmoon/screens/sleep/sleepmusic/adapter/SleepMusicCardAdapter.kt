package com.example.silentmoon.screens.sleep.sleepmusic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.databinding.SleepItemBinding
import com.example.silentmoon.screens.sleep.sleepmusic.item.SleepMusicCardItem

class SleepMusicCardAdapter :
    ListAdapter<SleepMusicCardItem, SleepMusicCardAdapter.SleepViewHolder>(DiffUtilTopicCallback()) {


    private companion object {

        class DiffUtilTopicCallback : DiffUtil.ItemCallback<SleepMusicCardItem>() {

            override fun areItemsTheSame(
                oldItem: SleepMusicCardItem,
                newItem: SleepMusicCardItem
            ): Boolean {
                return oldItem.imageId == newItem.imageId
            }

            override fun areContentsTheSame(
                oldItem: SleepMusicCardItem,
                newItem: SleepMusicCardItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class SleepViewHolder(val binding: SleepItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        val binding = SleepItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return SleepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            setImageResource(cardImage, item.imageId)
            cardLabel.text = item.text
        }
    }


    private fun setImageResource(imageView: ImageView, imageId: Int) {
        imageView.setImageResource(imageId)
    }


}