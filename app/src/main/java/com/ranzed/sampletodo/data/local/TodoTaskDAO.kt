package com.ranzed.sampletodo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoTaskDAO {

    @Query("SELECT * FROM TodoTaskEntity")
    fun loadAllTodoTasks() : List<TodoTaskEntity>

    @Query("SELECT * FROM TodoTaskEntity WHERE id=:taskId")
    fun getTodoTask(taskId : Int) : TodoTaskEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTodoTask(t : TodoTaskEntity)

    @Query("DELETE FROM TodoTaskEntity WHERE id=:taskId")
    fun deleteTodoTask(taskId : Int)

}