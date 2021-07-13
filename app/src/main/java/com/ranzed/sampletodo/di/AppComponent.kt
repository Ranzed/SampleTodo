package com.ranzed.sampletodo.di

import android.content.Context
import com.ranzed.sampletodo.MainActivity
import com.ranzed.sampletodo.data.TodoTaskRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ctx : Context) : AppComponent
    }

    fun repo() : TodoTaskRepository

    fun inject(a : MainActivity)
}