package com.ranzed.sampletodo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoTaskEntity::class], version = 1, exportSchema = false)
abstract class TodoTaskDB : RoomDatabase() {
    abstract fun dao(): TodoTaskDAO
}