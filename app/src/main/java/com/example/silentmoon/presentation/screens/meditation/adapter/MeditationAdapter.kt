package com.example.silentmoon.presentation.screens.meditation.adapter

import android.app.Activity
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.databinding.MeditationItemBinding
import com.example.silentmoon.presentation.screens.meditation.entity.MeditationItem
import eightbitlab.com.blurview.RenderEffectBlur

class MeditationAdapter(private inline val onItemClick: () -> Unit) :
    ListAdapter<MeditationItem, MeditationAdapter.MeditationViewHolder>(DiffUtilTopicCallback()) {


    private companion object {
        const val MAXIMUM_CARD_HEIGHT = 220F
        const val MINIMUM_CARD_HEIGHT = 167F
        const val MAXIMUM_CARD_WIDTH = 177F

        class DiffUtilTopicCallback : DiffUtil.ItemCallback<MeditationItem>() {

            override fun areItemsTheSame(
                oldItem: MeditationItem,
                newItem: MeditationItem
            ): Boolean {
                return oldItem.imageId == newItem.imageId
            }

            override fun areContentsTheSame(
                oldItem: MeditationItem,
                newItem: MeditationItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MeditationViewHolder(val binding: MeditationItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder {
        val binding = MeditationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        val activity = parent.context as? Activity
        val window = activity?.window
        val decorView = window?.decorView
        val windowBackground = decorView?.background
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.blurView.clipToOutline = true;

        binding.blurView.setupWith(binding.root, RenderEffectBlur())
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(9f)

        return MeditationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            root.setOnClickListener { onItemClick() }
            setImageResource(cardBackground, item.imageId)
            setTextViewAttribute(meditationItemLabel, root.context.resources.getString(item.title))

            val dpValue =
                if (position % 2 == 0) MAXIMUM_CARD_HEIGHT else MINIMUM_CARD_HEIGHT
            val pixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                root.resources.displayMetrics
            )
            val widthPixel = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                MAXIMUM_CARD_WIDTH,
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
        textView: TextView,
        label: String,
    ) {
        textView.text = label
    }


}
