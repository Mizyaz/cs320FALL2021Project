package com.example.android.navigation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import com.example.android.navigation.placeholder.PlaceholderContent.PlaceholderItem

class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder{
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_main_tasks_page, parent, false)

        val holder = ItemViewHolder(adapterLayout)


        holder.itemView.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_mainTasksPageFragment_to_individualTaskPage)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

}