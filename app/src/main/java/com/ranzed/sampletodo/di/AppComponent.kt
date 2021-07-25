package com.ranzed.sampletodo.di


import android.content.Context
import com.ranzed.sampletodo.MainActivity
import com.ranzed.sampletodo.domain.usecase.ShowDetail
import com.ranzed.sampletodo.domain.usecase.ShowList
import com.ranzed.sampletodo.presentation.viewmodel.TodoListViewModel
import com.ranzed.sampletodo.presentation.viewmodel.TodoTaskViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ctx : Context) : AppComponent
    }


    fun showListUC() : ShowList

    fun showDetailUC() : ShowDetail

    fun inject(a : MainActivity)
    fun inject(vm: TodoListViewModel)
    fun inject(vm: TodoTaskViewModel)
}