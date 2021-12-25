/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.android.navigation.database.Task
import com.example.android.navigation.database.TaskDatabase
import com.example.android.navigation.database.TaskDatabaseDao
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {

    private lateinit var taskDatabaseDao: TaskDatabaseDao
    private lateinit var db: TaskDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        taskDatabaseDao = db.taskDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val task = Task()
        taskDatabaseDao.insert(task)
        val tonight = taskDatabaseDao.getCurrentTask()
        taskDatabaseDao.getAllTasks()
        taskDatabaseDao.getAllTasksFilteredByPriority("important")
        taskDatabaseDao.update(task)

        task.equals(task)
        task.hashCode()
        task.toString()
        task.copy()
        task.Descryption = "test"
        task.PriorityLevel = "important"
        task.archived = true
        task.StartDate = "26/12/2021"
        task.EndDate = "26/12/2021"
        task.Id = 5

        taskDatabaseDao.get(5)

        taskDatabaseDao.remove(5)

        taskDatabaseDao.clear()

        task.toHTMLString()

        assertEquals(tonight?.PriorityLevel, " ")

    }
}


