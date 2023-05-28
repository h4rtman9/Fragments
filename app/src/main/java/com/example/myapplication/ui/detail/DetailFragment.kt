package com.example.myapplication.ui.detail

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.EditDialogBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Item
import com.example.myapplication.databinding.PopupMenuBinding
import com.example.myapplication.databinding.TextRowItemBinding


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var EditBinding: EditDialogBinding
    private lateinit var adapter: DetailAdapter
    private val list: MutableList<Item> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        setData()
    }

    private fun setData() {
        binding.apply {
            adapter = DetailAdapter(requireContext())
            recyclerViewDetail.adapter = adapter
            adapter.items = getData().toMutableList()

            adapter.setOnClickListener { i, v ->
//               showMenu(v)
            }

            adapter.setOnItemChange {
               i, pos ->  changeItem(i, pos)
            }
            adapter.setOnItemDelete {
              i, pos ->   list.removeAt(pos)
            }
        }
    }

    private fun changeItem(p: Item, pos: Int) {
        // Show Custom Dialog
        // Save changes
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.edit_dialog)
        dialog.show()
        EditBinding.btnChange.setOnClickListener {
            if (EditBinding.evName.text.toString() !=""){
                adapter.items[pos].text = EditBinding.evName.text.toString()
                adapter.notifyItemChanged(pos)
            }
        }

    }

    private fun getData(): List<Item> {
        val list: MutableList<Item> = mutableListOf()
        repeat(15) {
            list.add(
                Item(
                    "0",
                    "",
                    0,
                    "Description $it",
                    icon =  R.drawable.rectangle_dog
                )
            )
        }
        return list
    }


}