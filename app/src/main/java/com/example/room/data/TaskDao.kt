package com.example.room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Query("SELECT * FROM task")
    fun getTasks(): List<Task>

    @Delete
        fun deleteTask(task: Task)

    @Query("SELECT* FROM Task ORDER BY task")
    fun getListByAlphabet():List<Task>

}