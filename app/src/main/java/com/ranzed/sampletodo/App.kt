package com.ranzed.sampletodo

import android.app.Application
import com.ranzed.sampletodo.di.DaggerAppComponent

class App : Application() {

    val appComponent = DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
    }
}