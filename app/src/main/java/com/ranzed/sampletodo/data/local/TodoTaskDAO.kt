package com.ranzed.sampletodo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoTaskDAO {

    @Query("SELECT * FROM TodoTaskEntity")
    suspend fun loadAllTodoTasks(): List<TodoTaskEntity>

    @Query("SELECT * FROM TodoTaskEntity WHERE id=:taskId")
    suspend fun getTodoTask(taskId: Int): TodoTaskEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodoTask(t: TodoTaskEntity): Long

    @Query("DELETE FROM TodoTaskEntity WHERE id=:taskId")
    suspend fun deleteTodoTask(taskId: Int)

}