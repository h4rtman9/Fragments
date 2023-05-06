package com.example.myapplication.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.data.Item
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.info.InfoFragment

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)


        setData()

    }

    private fun setData() {
        binding.apply {
            adapter = ListAdapter()
            recyclerView.adapter = adapter

            adapter.items = getData().toMutableList()

            adapter.setOnClickListener {
                val f = InfoFragment()

                val b = Bundle()
                b.putString("name", it.name)
                b.putString("text", it.text)

                f.arguments = b

                (context as MainActivity).openFragment(f)
            }
        }
    }


    private fun getData(): List<Item> {
        val list: MutableList<Item> = mutableListOf()
        repeat(15) {
            list.add(
                Item(
                    "$it",
                    "name $it",
                    it + 15,
                    "Description $it",
                    icon = if (it % 2 == 1) R.drawable.baseline_person_outline_24 else R.drawable.omen
                )
            )
        }
        return list
    }

}