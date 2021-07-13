package com.ranzed.sampletodo

import android.app.Application
import com.ranzed.sampletodo.di.DaggerAppComponent

class App : Application() {

    val appComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
    }
}