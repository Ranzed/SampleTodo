package com.ranzed.sampletodo.domain.interfaces

import com.ranzed.sampletodo.domain.TodoTask

interface ITodoTaskRepository {

    suspend fun loadAllTodoTasks(): List<TodoTask>

    suspend fun getTodoTask(id: Int): TodoTask

    suspend fun saveTodoTask(t: TodoTask)

    suspend fun deleteTodoTask(id: Int)
}