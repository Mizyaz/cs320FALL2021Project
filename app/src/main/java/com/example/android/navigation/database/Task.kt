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

import android.text.Html
import android.text.Spanned
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.navigation.formatTask


@Entity(tableName = "MOCK_DATA")
data class Task(
        @PrimaryKey(autoGenerate = true)
        var Id: Long = 0L,

        @ColumnInfo(name = "descryption")
        var Descryption: String = " ",

        @ColumnInfo(name = "start_date")
        var StartDate: String = " ",

        @ColumnInfo(name = "end_date")
        var EndDate: String = " ",

        @ColumnInfo(name = "priority_level")
        var PriorityLevel: String = " "

)
{
        fun toHTMLString(): Spanned = formatTask(this)
}