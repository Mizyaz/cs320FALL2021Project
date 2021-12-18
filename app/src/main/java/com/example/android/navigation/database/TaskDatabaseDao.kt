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


/**
 * Defines methods for using the SleepNight class with Room.
 */
@Dao
interface TaskDatabaseDao {

    @Insert
    suspend fun insert(task: Task)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param task new value to write
     */
    @Update
    suspend  fun update(task: Task)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key index to match
     */
    @Query("SELECT * from MOCK_DATA WHERE Id = :key")
    suspend fun get(key: Long): Task?
    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM MOCK_DATA")
    suspend fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by index in descending order.
     */
    @Query("SELECT * FROM MOCK_DATA ORDER BY Id DESC")
    fun getAllTasks(): LiveData<List<Task>>

    /**
     * Selects and returns the latest task.
     */
    @Query("SELECT * FROM MOCK_DATA ORDER BY Id DESC LIMIT 1")
    suspend fun getCurrentTask(): Task?
}
