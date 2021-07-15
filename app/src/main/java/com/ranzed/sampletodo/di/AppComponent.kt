package com.ranzed.sampletodo.di


import android.content.Context
import com.ranzed.sampletodo.MainActivity
import com.ranzed.sampletodo.data.TodoTaskRepository
import com.ranzed.sampletodo.domain.usecase.ShowDetail
import com.ranzed.sampletodo.domain.usecase.ShowList
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, LocalStoreModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance ctx : Context) : AppComponent
    }


    fun showListUC() : ShowList

    fun showDetailUC() : ShowDetail

    fun inject(a : MainActivity)
}