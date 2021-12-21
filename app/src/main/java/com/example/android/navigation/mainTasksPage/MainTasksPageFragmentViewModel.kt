package com.example.android.navigation.mainTasksPage

import android.app.Application
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

    fun onClickFAB(textInput: String) {
        viewModelScope.launch {

            val newTask = Task()

            newTask.Descryption = textInput

            insert(newTask)

            currentTask.value = getCurrentTaskFromDatabase()
        }
    }

    fun onClickFAB2() {
        viewModelScope.launch {
            onClear()
        }
    }

    fun onClickFABFilter() {
        viewModelScope.launch {
            tasks = database.getAllTasksFilteredByPriority("important")
        }
    }

    fun onClickFABDisplay() {

        viewModelScope.launch {
            tasks = database.getAllTasks()
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