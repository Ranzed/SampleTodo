package com.ranzed.sampletodo.domain

import java.util.*

data class TodoTask(
    val id : Int,
    val Title : String,
    val Description : String?,
    val Datetime : Date,
    val IsDone : Boolean
)