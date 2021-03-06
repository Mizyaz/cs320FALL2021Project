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

package com.example.android.navigation.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDatabaseDao {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query("SELECT * from MOCK_DATA WHERE Id = :key")
    fun get(key: Long): Task?

    @Query("DELETE FROM MOCK_DATA")
    fun clear()

    @Query("SELECT * FROM MOCK_DATA ORDER BY Id DESC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM MOCK_DATA ORDER BY Id DESC LIMIT 1")
    fun getCurrentTask(): Task?

    @Query("DELETE FROM MOCK_DATA WHERE Id = :key")
    fun remove(key: Long)

    @Query("SELECT * FROM MOCK_DATA WHERE priority_level = :priority ORDER BY Id DESC ")
    fun getAllTasksFilteredByPriority(priority: String): LiveData<List<Task>>

    @Query("SELECT * FROM MOCK_DATA WHERE archived = :isArchived ORDER BY Id DESC ")
    fun getAllTasksFilteredByArchived(isArchived : Boolean): LiveData<List<Task>>

}
