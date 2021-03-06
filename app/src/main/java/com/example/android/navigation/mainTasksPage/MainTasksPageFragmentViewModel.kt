package com.example.android.navigation.mainTasksPage

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.navigation.database.Task
import com.example.android.navigation.database.TaskDatabaseDao
import kotlinx.coroutines.launch

class MainTasksPageFragmentViewModel(
    val database: TaskDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var currentTask = MutableLiveData<Task?>()

    var tasks = database.getAllTasks()

    init {
        initializeTask()
    }

    private fun initializeTask() {
        viewModelScope.launch {
            currentTask.value = getCurrentTaskFromDatabase()
        }
    }

    private fun getCurrentTaskFromDatabase(): Task? {
        val task = database.getCurrentTask()

        return task
    }

    private fun insert(task: Task) {
        database.insert(task)
    }

    private fun update(task: Task) {
        database.update(task)
    }

    private fun clear() {
        database.clear()
    }

    fun addTask(textInput: String) {
        viewModelScope.launch {

            val newTask = Task()

            newTask.Descryption = textInput

            insert(newTask)

            currentTask.value = getCurrentTaskFromDatabase()
        }
    }

    fun removeAll() {
        viewModelScope.launch {
            onClear()
        }
    }

    fun filter() {
        viewModelScope.launch {
            tasks = database.getAllTasksFilteredByPriority("important")
        }
    }

    fun displayAll() {

        viewModelScope.launch {
            tasks = database.getAllTasks()
        }

    }

    fun showArchived() {

        viewModelScope.launch {

            tasks = database.getAllTasksFilteredByArchived(true)

        }

    }

    /**
     * Executes when the CLEAR button is clicked.
     */
    fun onClear() {
        viewModelScope.launch {
            // Clear the database table.
            clear()

            // And clear tonight since it's no longer in the database
            currentTask.value = null

        }
    }

}