package com.ranzed.sampletodo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val Title: String,
    val Description: String?,
    val Datetime: Long,
    val IsDone: Boolean,
)