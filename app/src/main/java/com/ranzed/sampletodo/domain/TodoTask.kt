package com.ranzed.sampletodo.domain

import java.util.Date

data class TodoTask(
    val id: Int,
    val Title: String,
    val Description: String?,
    val Datetime: Date,
    val IsDone: Boolean,
) {
    companion object {
        fun createEmpty() = TodoTask(0, "", null, Date(0), false)
    }
}