package com.ranzed.sampletodo.domain.usecase

import com.ranzed.sampletodo.domain.interfaces.ITodoTaskNavigation
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskRepository
import javax.inject.Inject

class ShowDetail @Inject constructor(
    private val repo : ITodoTaskRepository,
    private val navigation: ITodoTaskNavigation) {


}