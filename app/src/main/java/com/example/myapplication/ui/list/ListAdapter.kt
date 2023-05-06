package com.example.myapplication.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Item
import com.example.myapplication.databinding.ItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    var items: MutableList<Item> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onItemClick: (i: Item) -> Unit = {  }
    fun setOnClickListener(onItemClick: (i: Item) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class ListViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun showItems(item: Item) {
            binding.apply {
                textViewName.text = item.name
                textViewSize.text = item.size.toString()
                imageViewIcon.setImageResource(item.icon)
                root.setOnClickListener {
                    onItemClick.invoke(item)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, null, false)
        val binding = ItemBinding.bind(view)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListAdapter.ListViewHolder, position: Int) {
        holder.showItems(items[position])
    }

    override fun getItemCount(): Int = items.size
}