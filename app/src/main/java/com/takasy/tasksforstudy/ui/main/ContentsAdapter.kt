package com.takasy.tasksforstudy.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.takasy.tasksforstudy.R
import com.takasy.tasksforstudy.databinding.ContentHolderBinding

class ContentsAdapter(
    private val list: List<Content>
): RecyclerView.Adapter<ContentsAdapter.ContentHolder>() {

    data class Content(
        private val name: String,
        private val imageColor: Color
    ) {
        enum class Color(private val imageId: Int) {
            ORANGE(R.drawable.img_content_android_orange),
            BLUE(R.drawable.img_content_android_blue),
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentHolder(
            ContentHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ContentHolder, position: Int) {
        holder.binding.name.text = "Name $position"
        holder.binding.image.setImageResource(
            if (position % 2 == 0) R.drawable.img_content_android_blue
            else R.drawable.img_content_android_orange
        )
    }

    override fun getItemCount() = list.size

    inner class ContentHolder(
        val binding: ContentHolderBinding
    ) : RecyclerView.ViewHolder(binding.root)
}