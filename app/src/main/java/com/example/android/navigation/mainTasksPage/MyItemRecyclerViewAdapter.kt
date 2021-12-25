package com.example.android.navigation.mainTasksPage

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.android.navigation.R
import com.example.android.navigation.database.Task
import com.example.android.navigation.databinding.FragmentMainTasksPageListItemBinding
import com.google.android.play.core.splitinstall.d


class MyItemRecyclerViewAdapter : ListAdapter<Task, MyItemRecyclerViewAdapter.ViewHolder>(SleepNightDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val holder = ViewHolder.from(parent)

        holder.binding.displayButton.setOnClickListener{ view ->

            val direction = MainTasksPageFragmentDirections.actionMainTasksPageFragmentToIndividualTaskPage().setTaskId(holder.binding.task?.Id.toString())
            view.findNavController().navigate(direction)

        }

        return holder
    }

    class ViewHolder private constructor(val binding: FragmentMainTasksPageListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Task) {
            binding.task = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentMainTasksPageListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}


class SleepNightDiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.Id == newItem.Id
    }


    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }


}