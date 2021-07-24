package com.ranzed.sampletodo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import com.ranzed.sampletodo.domain.usecase.ShowDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoListViewModel : ViewModel() {

    @Inject lateinit var repo : ITodoTaskRepository
    @Inject lateinit var showDetail: ShowDetail

    val TodoTasks : MutableLiveData<List<TodoTask>> = MutableLiveData()

    val IsLoading : MutableLiveData<Boolean> = MutableLiveData(true)

    fun load() {
        IsLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = repo.loadAllTodoTasks()
            TodoTasks.postValue(tasks)
            IsLoading.postValue(false)
        }
    }

    fun clickCreateBtn() {
        showDetail.run()
    }

    fun clickTodoItem(id : Int) {
        showDetail.run(id)
    }
}