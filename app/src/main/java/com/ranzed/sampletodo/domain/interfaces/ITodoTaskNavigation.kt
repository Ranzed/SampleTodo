package com.ranzed.sampletodo.domain.interfaces

interface ITodoTaskNavigation {

    fun showTodoTasksList()

    fun showTodoTaskItem(id : Int)

    fun showPreviousPage()

    // instead of SingleLiveEvent
    fun showSnackbar(resId : Int)

}