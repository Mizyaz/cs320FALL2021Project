package com.example.android.navigation.mainTasksPage

import android.app.Application
import androidx.lifecycle.*
import com.example.android.navigation.database.Task
import com.example.android.navigation.database.TaskDatabaseDao
import com.example.android.navigation.formatTasks
import kotlinx.coroutines.launch

class MainTasksPageFragmentViewModel(
    val database: TaskDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var currentTask = MutableLiveData<Task?>()

    val tasks = database.getAllTasks()

    init {
        initializeTask()
    }

    private fun initializeTask() {
        viewModelScope.launch {
            currentTask.value = getCurrentTaskFromDatabase()
        }
    }

    private suspend fun getCurrentTaskFromDatabase(): Task? {
        val task = database.getCurrentTask()

        return task
    }

    private suspend fun insert(task: Task) {
        database.insert(task)
    }

    private suspend fun update(task: Task) {
        database.update(task)
    }

    private suspend fun clear() {
        database.clear()
    }

    fun onClickFAB() {
        viewModelScope.launch {
            // Create a new night, which captures the current time,
            // and insert it into the database.
            val newTask = Task()

            newTask.Descryption = "ssssssssss"

            insert(newTask)

            currentTask.value = getCurrentTaskFromDatabase()
        }
    }

    fun onClickFAB2() {
        viewModelScope.launch {
            onClear()
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