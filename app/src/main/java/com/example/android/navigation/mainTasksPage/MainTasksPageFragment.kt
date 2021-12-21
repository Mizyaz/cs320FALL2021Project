package com.example.android.navigation.mainTasksPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.navigation.R
import com.example.android.navigation.database.TaskDatabase
import com.example.android.navigation.databinding.FragmentMainTasksPageBinding

/**
 * A fragment representing a list of Items.
 */
class MainTasksPageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentMainTasksPageBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main_tasks_page, container, false)

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = TaskDatabase.getInstance(application).taskDatabaseDao
        val viewModelFactory = MainTasksPageFragmentViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val mainTasksPageFragmentViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(MainTasksPageFragmentViewModel::class.java)

        binding.taskViewModel = mainTasksPageFragmentViewModel

        val adapter = MyItemRecyclerViewAdapter()
        binding.taskList.adapter = adapter

        val fab = binding.addTask

        fab.setOnClickListener {

            val editText : EditText? = binding.textInput.editText
            mainTasksPageFragmentViewModel.onClickFAB(editText?.text.toString())
            editText?.setText(" ")
        }

        val fab2 = binding.removeAllTasks

        fab2.setOnClickListener {

            mainTasksPageFragmentViewModel.onClickFAB2()

        }

        val fabFilter = binding.filterByImportance

        fabFilter.setOnClickListener {

            mainTasksPageFragmentViewModel.onClickFABFilter()

            mainTasksPageFragmentViewModel.tasks.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })

        }

        val fabDisplay = binding.displayAllTasks

        fabDisplay.setOnClickListener {

            mainTasksPageFragmentViewModel.onClickFABDisplay()

            mainTasksPageFragmentViewModel.tasks.observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.submitList(it)
                }
            })

        }

        val manager = GridLayoutManager(activity, 1)
        binding.taskList.layoutManager = manager

        return binding.root
    }

}