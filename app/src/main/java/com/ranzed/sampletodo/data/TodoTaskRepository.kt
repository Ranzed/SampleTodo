package com.ranzed.sampletodo.data

import LocalDataSource
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import javax.inject.Inject

/**
 * Only local datasource, pretty easy repository.
 */
class TodoTaskRepository @Inject constructor(private val repo : LocalDataSource) : ITodoTaskRepository {

    override fun loadAllTodoTasks(): List<TodoTask> {
        return repo.loadAllTodoTasks()
    }

    override fun getTodoTask(id: Int): TodoTask {
        return repo.getTodoTask(id)
    }

    override fun saveTodoTask(t: TodoTask) {
        repo.saveTodoTask(t)
    }

    override fun deleteTodoTask(id: Int) {
        repo.deleteTodoTask(id)
    }
}