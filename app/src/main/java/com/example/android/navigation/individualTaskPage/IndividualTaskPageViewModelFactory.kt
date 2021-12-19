package com.example.android.navigation.individualTaskPage

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.navigation.database.TaskDatabaseDao
import com.example.android.navigation.individualTaskPage.IndividualTaskPageViewModel
import com.example.android.navigation.mainTasksPage.MainTasksPageFragmentViewModel

class IndividualTaskPageViewModelFactory(
    private val dataSource: TaskDatabaseDao,
    private val application: Application, private val key: Long) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IndividualTaskPageViewModel::class.java)) {
            return IndividualTaskPageViewModel(dataSource, application, key) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}