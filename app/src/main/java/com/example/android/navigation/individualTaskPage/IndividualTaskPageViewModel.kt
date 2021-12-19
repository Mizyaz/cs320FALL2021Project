package com.example.android.navigation.individualTaskPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.navigation.database.Task
import com.example.android.navigation.database.TaskDatabaseDao
import kotlinx.coroutines.launch

class IndividualTaskPageViewModel(
    val database: TaskDatabaseDao,
    application: Application, key: Long
    ) : AndroidViewModel(application) {

    val task : Task
    var text : String


    init {

        task = database.get(key)!!
        text = task.Descryption

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

    private fun get(key : Long): Task? {
        return database.get(key)
    }

    private fun remove(key: Long){
        database.remove(key)
    }

    fun onClickRemove(){

        remove(task.Id)

    }

}