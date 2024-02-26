package com.example.silentmoon.screens.choosetopic.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ChooseTopicItemBinding
import com.example.silentmoon.screens.choosetopic.item.TopicItem

class TopicAdapter(private inline val onItemClick: () -> Unit) :
    ListAdapter<TopicItem, TopicAdapter.TopicViewHolder>(DiffUtilTopicCallback()) {


    private companion object {
        const val MAXIMUM_CARD_HEIGHT = 220F
        const val MINIMUM_CARD_HEIGHT = 167F

        class DiffUtilTopicCallback : DiffUtil.ItemCallback<TopicItem>() {

            override fun areItemsTheSame(oldItem: TopicItem, newItem: TopicItem): Boolean {
                return oldItem.imageViewId == newItem.imageViewId
            }

            override fun areContentsTheSame(oldItem: TopicItem, newItem: TopicItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    class TopicViewHolder(val binding: ChooseTopicItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val binding = ChooseTopicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TopicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            root.setOnClickListener { onItemClick() }
            setImageResource(imageView3, item.imageViewId)
            setTextViewAttribute(root.context, textView6, item.label, item.textColor)
            val drawable =
                ContextCompat.getDrawable(root.context, R.drawable.rounded_card) as GradientDrawable
            val color = ContextCompat.getColor(root.context, item.backgroundColor)
            drawable.setColor(color)
            root.background = drawable

            val dpValue =
                if (position % 2 == 0) MAXIMUM_CARD_HEIGHT else MINIMUM_CARD_HEIGHT
            val pixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                root.resources.displayMetrics
            )
            val params = root.layoutParams
            params.height = pixels.toInt()
            root.layoutParams = params
        }
    }


    private fun setImageResource(imageView: ImageView, imageId: Int) {
        imageView.setImageResource(imageId)
    }

    private fun setTextViewAttribute(
        context: Context,
        textView: TextView,
        label: String,
        textColorId: Int
    ) {
        textView.text = label
        textView.setTextColor(ContextCompat.getColor(context, textColorId))
    }


}

