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
        val name: String,
        val imageColor: Color
    ) {
        enum class Color(val imageId: Int) {
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
        val content = list[position]
        holder.binding.name.text = content.name
        holder.binding.image.setImageResource(content.imageColor.imageId)
    }

    override fun getItemCount() = list.size

    inner class ContentHolder(
        val binding: ContentHolderBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
