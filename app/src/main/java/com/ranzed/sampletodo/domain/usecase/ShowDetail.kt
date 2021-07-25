package com.ranzed.sampletodo.domain.usecase

import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskNavigation
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import javax.inject.Inject

class ShowDetail @Inject constructor(
    private val repo : ITodoTaskRepository,
    private val navigation: ITodoTaskNavigation) {

    fun run(id : Int) {
        navigation.showTodoTaskItem(id)
    }

    fun run() {
        navigation.showTodoTaskItem(0)
    }

    fun saveTodoTask(t : TodoTask) {
        if (t.Title.isEmpty()) {
            navigation.showSnackbar(R.string.toast_title_empty)
            return
        }
        repo.saveTodoTask(t)
        navigation.showPreviousPage()
        navigation.showSnackbar(R.string.toast_saved)
    }

    fun deleteTodoTask(id : Int) {
        repo.deleteTodoTask(id)
        navigation.showPreviousPage()
        navigation.showSnackbar(R.string.toast_deleted)
    }


}