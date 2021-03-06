package com.example.android.navigation.individualTaskPage

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.android.navigation.R
import com.example.android.navigation.database.TaskDatabase
import com.example.android.navigation.databinding.IndividualTaskPageFragmentBinding
import java.util.*

class IndividualTaskPage : Fragment() {

    private val args: IndividualTaskPageArgs by navArgs()
    lateinit var binding: IndividualTaskPageFragmentBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        // Get a reference to the binding object and inflate the fragment views.
        binding = DataBindingUtil.inflate(
            inflater, R.layout.individual_task_page_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        // Create an instance of the ViewModel Factory.
        val dataSource = TaskDatabase.getInstance(application).taskDatabaseDao

        var taskId = args.taskId

        if(taskId.isNullOrBlank())
            taskId = 1.toString()
        
        val viewModelFactory =
            IndividualTaskPageViewModelFactory(dataSource, application, taskId.toLong())

        // Get a reference to the ViewModel associated with this fragment.
        val individualTaskPageViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(IndividualTaskPageViewModel::class.java)

        binding.individualTaskViewModel = individualTaskPageViewModel

        val currentTaskDescription: String = individualTaskPageViewModel.text

        args.taskId?.let { Log.d("TAG", it) }
        currentTaskDescription.let {
            Log.d("TAG", it)
        }

        binding.remove.setOnClickListener {
            var builder = AlertDialog.Builder(activity)
            builder.setTitle("Confirm Delete")
            builder.setMessage("Are you sure about removing the task ?")
            builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                individualTaskPageViewModel.onClickRemove()
                dialog.cancel()
            })
            builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })
            var alert = builder.create()
            alert.show()

        }

        val calendar = Calendar.getInstance()
        var end_year = calendar.get(Calendar.YEAR)
        var end_month = calendar.get(Calendar.MONTH)
        var end_day = calendar.get(Calendar.DAY_OF_MONTH)

        var start_year = calendar.get(Calendar.YEAR)
        var start_month = calendar.get(Calendar.MONTH)
        var start_day = calendar.get(Calendar.DAY_OF_MONTH)

        binding.addDate.setOnClickListener {

            val dpd = DatePickerDialog(context!!,DatePickerDialog.OnDateSetListener{view ,mYear,mMonth,mDay ->

                individualTaskPageViewModel.onAddStartDueDate(start_year, start_month, start_day, mYear,mMonth,mDay)

                end_day = mDay
                end_month = mMonth
                end_year = mYear
                binding.displayDate.setText(end_day.toString() + "/" + end_month.toString() + " /" + end_year.toString())
            },end_year,end_month,end_day)


            dpd.show()
        }

        binding.important.setOnClickListener {
            individualTaskPageViewModel.onAddPriority("important")
            Toast.makeText(context,"Successfully Selected Importance Level : Important",Toast.LENGTH_SHORT).show()
            binding.mainLayout.setBackgroundColor(resources.getColor(R.color.red))

        }

        binding.normal.setOnClickListener {

            individualTaskPageViewModel.onAddPriority("normal")
            Toast.makeText(context,"Successfully Selected Importance Level : Normal",Toast.LENGTH_SHORT).show()
            binding.mainLayout.setBackgroundColor(resources.getColor(R.color.green))

        }

        binding.light.setOnClickListener {

            individualTaskPageViewModel.onAddPriority("light")
            Toast.makeText(context,"Successfully Selected Importance Level : Light",Toast.LENGTH_SHORT).show()
            binding.mainLayout.setBackgroundColor(resources.getColor(R.color.yellow))

        }
        binding.importanceLevelButton.setOnClickListener{
            binding.light.visibility = View.VISIBLE
            binding.normal.visibility = View.VISIBLE
            binding.important.visibility = View.VISIBLE
        }

        binding.checkBox2.setOnClickListener {

            individualTaskPageViewModel.onClickArchive(binding.checkBox2.isChecked)
            if(binding.checkBox2.isChecked) {
                Toast.makeText(context, "Successfully archived the task", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(context, "Successfully unarchived the task", Toast.LENGTH_SHORT).show()

            }
        }

        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.light.visibility = View.INVISIBLE
        binding.normal.visibility = View.INVISIBLE
        binding.important.visibility = View.INVISIBLE
    }



}