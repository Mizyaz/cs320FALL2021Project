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

    var task : Task
    var text : String


    init {

        if(key.equals(null) && database.get(key)?.equals(null) == true) {
            task = database.get(key)!!
        } else {
            task = Task()
            insert(task)
        }

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

    fun onAddStartDueDate(startYear:Int,startMonth: Int,startDayOfWeek: Int, year:Int,month: Int,dayOfWeek: Int){

        task.StartDate = "$startDayOfWeek/$startMonth/$startYear"
        task.EndDate = "$dayOfWeek/$month/$year"

        update(task)

    }

    fun onClickArchive(isChecked: Boolean){

        task.archived = isChecked

    }

}