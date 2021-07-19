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
import java.lang.IllegalStateException
import java.util.*
import javax.inject.Inject

class TodoTaskViewModel : ViewModel() {

    @Inject lateinit var repo : ITodoTaskRepository
    @Inject lateinit var showDetail: ShowDetail
    @Inject lateinit var showList: ShowList

    private var TodoTask : TodoTask? = null

    val IsLoading : MutableLiveData<Boolean> = MutableLiveData(true)
    val Title : MutableLiveData<String> = MutableLiveData("")
    val Description : MutableLiveData<String> = MutableLiveData()
    val Datetime : MutableLiveData<String> = MutableLiveData()
    val CanSave : MutableLiveData<Boolean> = MutableLiveData(true)
    val CanDelete : MutableLiveData<Boolean> = MutableLiveData(false)

    private var dateTimeField : Date? = null

    fun load(id : Int) {
        IsLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val task = repo.getTodoTask(id)
            TodoTask = task
            Title.postValue(task.Title)
            Description.postValue(task.Description)
            dateTimeField = task.Datetime
            Datetime.postValue(formatDatetime(task.Datetime))
            IsLoading.postValue(false)
        }
    }

    fun setDateTime(d : Date) {
        dateTimeField = d
        Datetime.postValue(formatDatetime(d))
    }

    fun clickSave() {
        TodoTask = rebuildTodoTask()
        val task = TodoTask
        if (task != null)
            viewModelScope.launch(Dispatchers.IO) { showDetail.saveTodoTask(task) }
    }

    fun clickDelete() {
        val task = TodoTask
        if (task != null)
            showDetail.deleteTodoTask(task.id)
    }

    fun clickBack() {
        showList.run()
    }

    private fun rebuildTodoTask() : TodoTask {
        val t = TodoTask ?: throw IllegalStateException("Try save unloaded task")
        return TodoTask(t.id, Title.value ?: t.Title, Description.value, dateTimeField ?: Date(0), false )
    }

    private fun formatDatetime(d : Date) : String {
        return if (d.time > 0) d.toString() else ""
    }
}