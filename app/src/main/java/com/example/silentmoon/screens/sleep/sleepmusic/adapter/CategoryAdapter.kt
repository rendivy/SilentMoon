package com.example.silentmoon.screens.sleep.sleepmusic.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.SleepCategoriesItemBinding
import com.example.silentmoon.screens.sleep.sleepmusic.item.SleepCategoryItem

class CategoryAdapter(private val itemColor: Int? = null) :
    ListAdapter<SleepCategoryItem, CategoryAdapter.CategoryViewHolder>(
        CategoryAdapter.Companion.DiffUtilTopicCallback()
    ) {

    private var selectedPosition = 0

    private companion object {

        class DiffUtilTopicCallback : DiffUtil.ItemCallback<SleepCategoryItem>() {

            override fun areItemsTheSame(
                oldItem: SleepCategoryItem,
                newItem: SleepCategoryItem
            ): Boolean {
                return oldItem.imageId == newItem.imageId
            }

            override fun areContentsTheSame(
                oldItem: SleepCategoryItem,
                newItem: SleepCategoryItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class CategoryViewHolder(val binding: SleepCategoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = SleepCategoriesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return CategoryViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {


            imageButton.setImageResource(item.imageId)
            textView14.text = item.text
            imageButton.background = if (position == selectedPosition) {
                ContextCompat.getDrawable(
                    root.context,
                    R.drawable.category_item_selected_background
                )
            } else {
                ContextCompat.getDrawable(
                    root.context,
                    itemColor ?: R.drawable.category_item_unseletced_background
                )
            }


            with(holder.binding) {
                val paddingInDp = 24
                val paddingInPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    paddingInDp.toFloat(),
                    root.resources.displayMetrics
                )
                when (position) {
                    0 -> {
                        root.setPadding(paddingInPx.toInt(), 0, 0, 0)
                    }

                    itemCount - 1 -> {
                        root.setPadding(paddingInPx.toInt(), 0, paddingInPx.toInt(), 0)
                    }

                    else -> {
                        root.setPadding(paddingInPx.toInt(), 0, 0, 0)
                    }

                }
            }

            imageButton.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = holder.absoluteAdapterPosition
                notifyItemChanged(selectedPosition)
            }

        }
    }


}