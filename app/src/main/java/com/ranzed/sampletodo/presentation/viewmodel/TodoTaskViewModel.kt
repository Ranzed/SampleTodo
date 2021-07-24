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
import java.util.*
import javax.inject.Inject

class TodoTaskViewModel : ViewModel() {

    @Inject lateinit var repo : ITodoTaskRepository
    @Inject lateinit var showDetail: ShowDetail
    @Inject lateinit var showList: ShowList

    private var dateTimeField : Date? = null
    private var todoTaskId : Int = 0

    val IsLoading : MutableLiveData<Boolean> = MutableLiveData(true)
    val Title : MutableLiveData<String> = MutableLiveData("")
    val Description : MutableLiveData<String> = MutableLiveData()
    val Datetime : MutableLiveData<String> = MutableLiveData()
    val CanSave : MutableLiveData<Boolean> = MutableLiveData(false)
    val CanDelete : MutableLiveData<Boolean> = MutableLiveData(false)

    fun load(id : Int) {
        IsLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            CanSave.postValue(false)
            CanDelete.postValue(false)

            val task = repo.getTodoTask(id)
            todoTaskId = id
            Title.postValue(task.Title)
            Description.postValue(task.Description)
            dateTimeField = task.Datetime
            Datetime.postValue(formatDatetime(task.Datetime))
            IsLoading.postValue(false)
            CanSave.postValue(true) // после загрузки можем сохранять
            CanDelete.postValue(task.id != 0)
        }
    }

    fun setDateTime(d : Date) {
        dateTimeField = d
        Datetime.postValue(formatDatetime(d))
    }

    fun clickSave() {
        val task = rebuildTodoTask()
        if (task.Title.isEmpty()) {
            // snackbar
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            showDetail.saveTodoTask(task)
            showList.run()
        }
    }

    fun clickDelete() {
        showDetail.deleteTodoTask(todoTaskId)
        showList.run()
    }

    fun clickBack() {
        showList.run()
    }

    private fun rebuildTodoTask() : TodoTask {
        return TodoTask(todoTaskId,
            Title.value ?: "",
            Description.value,
            dateTimeField ?: Date(0), false )
    }

    private fun formatDatetime(d : Date) : String {
        return if (d.time > 0) d.toString() else ""
    }
}