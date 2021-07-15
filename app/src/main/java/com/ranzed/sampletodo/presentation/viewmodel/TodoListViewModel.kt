package com.ranzed.sampletodo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ranzed.sampletodo.domain.TodoTask

class TodoListViewModel() : ViewModel() {

    val TodoTasks : LiveData<List<TodoTask>> = MutableLiveData()

    val IsLoading : LiveData<Boolean> = MutableLiveData(true)

    fun init() {

    }

}