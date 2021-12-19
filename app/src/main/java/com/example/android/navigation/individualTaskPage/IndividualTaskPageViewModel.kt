package com.example.android.navigation.individualTaskPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.navigation.database.Task
import com.example.android.navigation.database.TaskDatabaseDao
import kotlinx.coroutines.launch

class IndividualTaskPageViewModel(
    val database: TaskDatabaseDao,
    application: Application
    ) : AndroidViewModel(application) {

    var text : String? = "task name"

    fun getCurrentTaskDescription(key : Long): String? {

        viewModelScope.launch {
            val task = get(key)
            text = task?.Descryption

        }

        return text

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

    private suspend fun get(key : Long): Task? {
        return database.get(key)
    }
}