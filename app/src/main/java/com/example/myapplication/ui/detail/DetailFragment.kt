package com.example.myapplication.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.navigation.fragment.findNavController
//import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.PopupMenuBinding


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var adapter: DetailAdapter
    private val list: MutableList<Int> = mutableListOf()

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
            adapter.pics = getData().toMutableList()

            adapter.setOnClickListener { i, v ->
//                showMenu(v)
//                findNavController().navigate(R.id.action_listFragment_to_infoFragment)
            }

            adapter.setOnItemChange {
//                list.remove(it)
                chageItem(it)
            }
            adapter.setOnItemDelete {
                list.remove(it)
            }
        }
    }

    private fun chageItem(p: Int) {
        // Show Custom Dialog
        // Save changes
    }

    private fun getData(): MutableList<Int> {

        repeat(15) {
            list.add(R.drawable.rectangle_dog)
        }
        return list
    }


}