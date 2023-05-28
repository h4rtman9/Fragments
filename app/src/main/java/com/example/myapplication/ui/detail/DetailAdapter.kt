package com.example.myapplication.ui.detail

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Item
import com.example.myapplication.databinding.PopupMenuBinding
import com.example.myapplication.databinding.TextRowItemBinding

class DetailAdapter(private val context: Context) :
    RecyclerView.Adapter<DetailAdapter.ListViewHolder>() {

    private var popupWindow: PopupWindow? = null
    var items: MutableList<Item> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onItemClick: (i: Int, view: View) -> Unit = { i, view -> }
    fun setOnClickListener(onItemClick: (i: Int, view: View) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class ListViewHolder(private val binding: TextRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun showItems(pic: Int, position: Int) {
            binding.apply {
                imageViewDog.setImageResource(R.drawable.rectangle_dog)
                root.setOnLongClickListener {
//                    onItemClick.invoke(pic, binding.root)
                    popupWindow?.dismiss()
                    showMenu(binding.root, pic, position)
                    return@setOnLongClickListener true
                }
                root.setOnClickListener {
                    popupWindow?.dismiss()
                }
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, null, false)
        val binding = TextRowItemBinding.bind(view)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailAdapter.ListViewHolder, position: Int) {
        holder.showItems(items[position].icon, position)
    }

    override fun getItemCount(): Int = items.size


    private var onItemChange: (i: Item, pos: Int) -> Unit = { i ,pos -> }
    fun setOnItemChange(onItemChange: (i: Item, pos: Int) -> Unit) {
        this.onItemChange = onItemChange
    }

    private var onItemDelete: (i: Item, pos: Int) -> Unit = { i, pos -> }
    fun setOnItemDelete(onItemDelete: (i: Item, pos: Int) -> Unit) {
        this.onItemDelete = onItemDelete
    }

    private fun showMenu(v: View, pic: Int, position: Int) {
        val popupInf =
            context.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val popBinding = PopupMenuBinding.inflate(popupInf)
        popupWindow = PopupWindow(
            popBinding.root,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            contentView.setOnClickListener { dismiss() }
        }

        popBinding.textViewCHange.setOnClickListener {
            onItemChange.invoke(items[position], position)
            popupWindow?.dismiss()

//            notifyItemChanged(position)
        }

        popBinding.textViewDelete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Confirm")
            builder.setMessage("You really want to delete this item?")
            builder.setPositiveButton("Yes") { dialogInterface, i ->
                popupWindow?.dismiss()
                onItemDelete.invoke(items[position],position)
                items.removeAt(position)
                notifyItemRemoved(position)
            }
            builder.setNegativeButton("No")  { dialogInterface, i ->
                popupWindow?.dismiss()
            }
            builder.show()

        }
        popupWindow?.showAsDropDown(v)

    }
//    private fun DialogConfirm(v: View, pic: Int, position: Int){
//
//        val builder = AlertDialog.Builder(context)
//        builder.setTitle("Confirm")
//        builder.setMessage("You really want to delete this item?")
//        builder.setPositiveButton("Yes") { dialogInterface, i ->
//
//        }
//        builder.setNegativeButton("No")  { dialogInterface, i -> }
//        builder.show()
//    }
}

