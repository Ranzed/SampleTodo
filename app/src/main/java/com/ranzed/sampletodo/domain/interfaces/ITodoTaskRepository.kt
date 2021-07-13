package com.ranzed.sampletodo.domain.interfaces

import com.ranzed.sampletodo.domain.TodoTask

interface ITodoTaskRepository {

    fun loadAllTodoTasks() : List<TodoTask>

    fun getTodoTask(id : Int) : TodoTask

    fun saveTodoTask(t : TodoTask)

    fun deleteTodoTask(id : Int)
}