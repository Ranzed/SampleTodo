package com.ranzed.sampletodo.data

import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Only local datasource, pretty easy repository.
 */
@Singleton
class TodoTaskRepository @Inject constructor(private val repo: LocalDataSource) :
    ITodoTaskRepository {

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