package com.ranzed.sampletodo.data

import android.util.Log
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
        Log.i(javaClass.name, "loadAllTodoTasks on thread " + Thread.currentThread().name)
        return repo.loadAllTodoTasks()
    }

    override fun getTodoTask(id: Int): TodoTask {
        Log.i(javaClass.name, "getTodoTask on thread " + Thread.currentThread().name)
        return repo.getTodoTask(id)
    }

    override fun saveTodoTask(t: TodoTask) {
        Log.i(javaClass.name, "saveTodoTask on thread " + Thread.currentThread().name)
        repo.saveTodoTask(t)
    }

    override fun deleteTodoTask(id: Int) {
        Log.i(javaClass.name, "deleteTodoTask on thread " + Thread.currentThread().name)
        repo.deleteTodoTask(id)
    }
}