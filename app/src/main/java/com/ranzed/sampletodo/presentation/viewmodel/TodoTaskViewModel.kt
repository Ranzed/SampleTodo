package com.ranzed.sampletodo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import com.ranzed.sampletodo.domain.usecase.ShowDetail
import com.ranzed.sampletodo.presentation.format
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class TodoTaskViewModel : ViewModel() {

    @Inject
    lateinit var repo: ITodoTaskRepository
    @Inject
    lateinit var showDetail: ShowDetail

    private var dateTimeField: Date? = null
    private var todoTaskId: Int = 0

    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val title: MutableLiveData<String> = MutableLiveData("")
    val description: MutableLiveData<String> = MutableLiveData()
    val datetime: MutableLiveData<String> = MutableLiveData()
    val isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val canSave: MutableLiveData<Boolean> = MutableLiveData(false)
    val canDelete: MutableLiveData<Boolean> = MutableLiveData(false)

    fun load(id: Int) {
        isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(javaClass.name, "start coroutine on thread " + Thread.currentThread().name)
            canSave.postValue(false)
            canDelete.postValue(false)

            val task = repo.getTodoTask(id)
            todoTaskId = id
            title.postValue(task.Title)
            description.postValue(task.Description)
            dateTimeField = task.Datetime
            datetime.postValue(task.Datetime.format())
            isDone.postValue(task.IsDone)

            canSave.postValue(true) // после загрузки можем сохранять
            canDelete.postValue(task.id != 0)
            isLoading.postValue(false)
        }
    }

    fun setDateTime(d: Date) {
        dateTimeField = d
        datetime.postValue(d.format())
    }

    fun clickSave() {
        val task = rebuildTodoTask()
        viewModelScope.launch(Dispatchers.IO) {
            showDetail.saveTodoTask(task)
        }
    }

    fun clickDelete() {
        viewModelScope.launch(Dispatchers.IO) {
            showDetail.deleteTodoTask(todoTaskId)
        }
    }

    private fun rebuildTodoTask(): TodoTask {
        return TodoTask(
            todoTaskId,
            title.value ?: "",
            description.value,
            dateTimeField ?: Date(0),
            isDone.value ?: false
        )
    }
}