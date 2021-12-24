package com.example.android.navigation.individualTaskPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.navigation.database.Task
import com.example.android.navigation.database.TaskDatabaseDao
import java.time.DayOfWeek
import java.time.Month
import java.time.Year

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

    fun onAddPriority(s: String) {

        task.PriorityLevel = s
        update(task)

    }

    fun onAddStartDueDate(year:Year,month: Month,dayOfWeek: DayOfWeek){

        task.StartDate = "${dayOfWeek.toString()}/${month.toString()}/${year.toString()}"
        task.EndDate = "25/19/2021"

        update(task)

    }

}