package com.example.android.navigation.mainTasksPage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.navigation.database.TaskDatabaseDao

class MainTasksPageFragmentViewModelFactory(
    private val dataSource: TaskDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainTasksPageFragmentViewModel::class.java)) {
            return MainTasksPageFragmentViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}