package com.example.scrollablexml

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollablexml.databinding.GameCardBinding

class FalloutAdapter(
    private val items: List<FalloutItem>,
    private val onWikiClick: (FalloutItem) -> Unit,
    private val onDetailClick: (FalloutItem) -> Unit
) : RecyclerView.Adapter<FalloutAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: GameCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description
            ivImg.setImageResource(item.imageResId)

            btnDetail.setOnClickListener {
                onDetailClick(item)
            }

            btnWiki.setOnClickListener {
                onWikiClick(item)
            }
        }
    }
}
