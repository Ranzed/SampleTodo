package com.ranzed.sampletodo.presentation.viewmodel

import android.util.Log
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

    @Inject
    lateinit var repo: ITodoTaskRepository
    @Inject
    lateinit var showDetail: ShowDetail

    val todoTasks: MutableLiveData<List<TodoTask>> = MutableLiveData()
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    fun load() {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(javaClass.name, "start coroutine on thread ${Thread.currentThread().name}")
            val tasks = repo.loadAllTodoTasks()
            todoTasks.postValue(tasks)
            isEmpty.postValue(tasks.count() == 0)
            isLoading.postValue(false)
        }
    }

    fun clickCreateBtn() = showDetail.run()
    fun clickTodoItem(id: Int) = showDetail.run(id)
}