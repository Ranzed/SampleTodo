package com.ranzed.sampletodo.di

import com.ranzed.sampletodo.data.TodoTaskRepository
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskNavigation
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import com.ranzed.sampletodo.presentation.TodoTaskNavigation
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun bindTodoTaskRepo(repo : TodoTaskRepository) : ITodoTaskRepository

    @Binds
    abstract fun bindTodoTaskNavigation(navigation: TodoTaskNavigation) : ITodoTaskNavigation
}