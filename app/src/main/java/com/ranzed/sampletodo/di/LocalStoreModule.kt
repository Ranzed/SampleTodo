package com.ranzed.sampletodo.di

import android.content.Context
import androidx.room.Room
import com.ranzed.sampletodo.data.local.TodoTaskDB
import dagger.Module
import dagger.Provides

@Module
class LocalStoreModule {

//    @Provides
//    fun provideDb(ctx : Context) : TodoTaskDB {
//        return Room.databaseBuilder(
//            ctx,
//            TodoTaskDB::class.java, "todotask_db"
//        ).build()
//    }
}