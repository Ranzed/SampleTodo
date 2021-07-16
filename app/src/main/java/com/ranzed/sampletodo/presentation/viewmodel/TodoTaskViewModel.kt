package com.ranzed.sampletodo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import com.ranzed.sampletodo.domain.usecase.ShowDetail
import com.ranzed.sampletodo.domain.usecase.ShowList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoTaskViewModel : ViewModel() {

    @Inject
    lateinit var repo : ITodoTaskRepository

    @Inject
    lateinit var showDetail: ShowDetail

    @Inject
    lateinit var showList: ShowList

    val TodoTasks : MutableLiveData<TodoTask> = MutableLiveData()

    val IsLoading : MutableLiveData<Boolean> = MutableLiveData(true)

    fun load(id : Int) {
        IsLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val task = repo.getTodoTask(id)
            TodoTasks.postValue(task)
            IsLoading.postValue(false)
        }
    }

    fun clickSave() {
        val task = TodoTasks.value
        if (task != null)
            showDetail.deleteTodoTask(task.id)
    }

    fun clickDelete() {
        val task = TodoTasks.value
        if (task != null)
            showDetail.deleteTodoTask(task.id)
    }

    fun clickBack() {
        showList.run()
    }
}