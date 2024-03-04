package com.example.silentmoon.presentation.screens.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.HomeItemBinding
import com.example.silentmoon.presentation.screens.home.entity.HomeItem

class HomeAdapter :
    ListAdapter<HomeItem, HomeAdapter.HomeViewHolder>(DiffUtilTopicCallback()) {

    private companion object {
        class DiffUtilTopicCallback : DiffUtil.ItemCallback<HomeItem>() {

            override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
                return oldItem.backgroundId == newItem.backgroundId
            }

            override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean {
                return oldItem == newItem
            }
        }
    }


    class HomeViewHolder(val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = HomeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        with(holder.binding.root) {
            val paddingLeft = if (position == 0) {
                resources.getDimensionPixelSize(R.dimen.padding0)
            } else {
                resources.getDimensionPixelSize(R.dimen.padding10)
            }
            val paddingRight = resources.getDimensionPixelSize(R.dimen.padding10)
            setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
        }
    }


}