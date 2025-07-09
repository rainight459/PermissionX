package com.example.practiceapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceapp.R
import com.example.practiceapp.data.Item
import com.example.practiceapp.utils.AnimationUtils

class ItemAdapter(
    private val items: MutableList<Item>,
    private val onItemClick: (Item) -> Unit,
    private val onItemDelete: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tvTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tvDescription)
        private val timestampTextView: TextView = itemView.findViewById(R.id.tvTimestamp)
        private val imageView: ImageView = itemView.findViewById(R.id.ivImage)
        private val deleteButton: Button = itemView.findViewById(R.id.btnDelete)
        
        fun bind(item: Item) {
            titleTextView.text = item.title
            descriptionTextView.text = item.description
            timestampTextView.text = formatTimestamp(item.timestamp)
            
            // 设置点击事件
            itemView.setOnClickListener {
                AnimationUtils.scaleAnimation(itemView, 1.0f, 0.95f, 150)
                onItemClick(item)
            }
            
            // 设置删除按钮点击事件
            deleteButton.setOnClickListener {
                AnimationUtils.bounceAnimation(deleteButton)
                onItemDelete(item)
            }
            
            // 设置长按事件
            itemView.setOnLongClickListener {
                AnimationUtils.shakeAnimation(itemView)
                true
            }
            
            // 应用进入动画
            AnimationUtils.enterAnimation(itemView)
        }
        
        private fun formatTimestamp(timestamp: Long): String {
            val diff = System.currentTimeMillis() - timestamp
            return when {
                diff < 60 * 1000 -> "刚刚"
                diff < 60 * 60 * 1000 -> "${diff / (60 * 1000)}分钟前"
                diff < 24 * 60 * 60 * 1000 -> "${diff / (60 * 60 * 1000)}小时前"
                else -> "${diff / (24 * 60 * 60 * 1000)}天前"
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
    
    override fun getItemCount(): Int = items.size
    
    fun updateItems(newItems: List<Item>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
    
    fun addItem(item: Item) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
    
    fun removeItem(item: Item) {
        val position = items.indexOf(item)
        if (position != -1) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}