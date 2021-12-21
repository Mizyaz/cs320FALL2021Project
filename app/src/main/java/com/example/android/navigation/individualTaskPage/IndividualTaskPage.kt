package com.example.android.navigation.individualTaskPage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.android.navigation.R
import com.example.android.navigation.database.TaskDatabase
import com.example.android.navigation.databinding.IndividualTaskPageFragmentBinding

class IndividualTaskPage : Fragment() {

    private val args: IndividualTaskPageArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: IndividualTaskPageFragmentBinding = DataBindingUtil.inflate(
                inflater, R.layout.individual_task_page_fragment, container, false)

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = TaskDatabase.getInstance(application).taskDatabaseDao

        val viewModelFactory = IndividualTaskPageViewModelFactory(dataSource, application, args.taskId.toLong())

        // Get a reference to the ViewModel associated with this fragment.
        val individualTaskPageViewModel =
                ViewModelProvider(
                        this, viewModelFactory).get(IndividualTaskPageViewModel::class.java)

        binding.individualTaskViewModel = individualTaskPageViewModel

        val currentTaskDescription : String = individualTaskPageViewModel.text

        Log.d("TAG", args.taskId)
        currentTaskDescription.let {
            Log.d("TAG", it)
        }

        binding.remove.setOnClickListener {

            individualTaskPageViewModel.onClickRemove()

        }

        binding.addDate.setOnClickListener {

            individualTaskPageViewModel.onAddStartDueDate()

        }

        binding.important.setOnClickListener {

            individualTaskPageViewModel.onAddPriority("important")

        }

        binding.normal.setOnClickListener {

            individualTaskPageViewModel.onAddPriority("normal")

        }

        binding.light.setOnClickListener {

            individualTaskPageViewModel.onAddPriority("light")

        }

        return binding.root
    }



}