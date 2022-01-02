package com.ranzed.sampletodo.domain.usecase

import android.util.Log
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskNavigation
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import javax.inject.Inject

class ShowList @Inject constructor(
    private val repo: ITodoTaskRepository,
    private val navigation: ITodoTaskNavigation
) {

    fun run() {
        navigation.showTodoTasksList()
        Log.i(javaClass.name, "run on thread " + Thread.currentThread().name)
    }

}