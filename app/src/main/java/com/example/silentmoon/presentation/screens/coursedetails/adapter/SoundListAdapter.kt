package com.example.silentmoon.presentation.screens.coursedetails.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.CourseItemBinding
import com.example.silentmoon.presentation.screens.coursedetails.entity.SoundItem

class SoundListAdapter(private inline val onItemClick: (Int) -> Unit) :
    ListAdapter<SoundItem, SoundListAdapter.SoundViewHolder>(DiffUtilTopicCallback()) {


    private companion object {
        class DiffUtilTopicCallback : DiffUtil.ItemCallback<SoundItem>() {

            override fun areItemsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
                return oldItem.labelId == newItem.labelId
            }

            override fun areContentsTheSame(oldItem: SoundItem, newItem: SoundItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    class SoundViewHolder(val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val binding = CourseItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            if (position == 0) {
                playButton.setBackgroundResource(R.drawable.category_item_selected_background)
                playButton.setColorFilter(Color.argb(255, 255, 255, 255))
            }
            playButton.setOnClickListener {
                onItemClick(item.labelId)
            }
            itemLabel.text = ContextCompat.getString(root.context, item.labelId)
            itemDuration.text = ContextCompat.getString(root.context, item.soundTimeId)
        }
    }


}