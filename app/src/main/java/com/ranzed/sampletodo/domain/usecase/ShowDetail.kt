package com.ranzed.sampletodo.domain.usecase

import android.util.Log
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskNavigation
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import javax.inject.Inject

class ShowDetail @Inject constructor(
    private val repo: ITodoTaskRepository,
    private val navigation: ITodoTaskNavigation
) {

    fun run(id: Int) {
        Log.i(javaClass.name, "run(id = " + id +") on thread " + Thread.currentThread().name)
        navigation.showTodoTaskItem(id)
    }

    fun run() {
        Log.i(javaClass.name, "run on thread " + Thread.currentThread().name)
        navigation.showTodoTaskItem(0)
    }

    suspend fun saveTodoTask(t: TodoTask) {
        Log.i(javaClass.name, "saveTodoTask on thread " + Thread.currentThread().name)
        if (t.Title.isEmpty()) {
            navigation.showSnackbar(R.string.toast_title_empty)
            return
        }
        repo.saveTodoTask(t)
        navigation.showPreviousPage()
        navigation.showSnackbar(R.string.toast_saved)
    }

    suspend fun deleteTodoTask(id: Int) {
        Log.i(javaClass.name, "deleteTodoTask on thread " + Thread.currentThread().name)
        repo.deleteTodoTask(id)
        navigation.showPreviousPage()
        navigation.showSnackbar(R.string.toast_deleted)
    }
}