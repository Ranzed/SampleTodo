package com.ranzed.sampletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.ranzed.sampletodo.domain.usecase.ShowList
import com.ranzed.sampletodo.presentation.TodoTaskNavigation
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var rootView : ViewGroup? = null

    @Inject
    lateinit var showListUseCase : ShowList

    @Inject
    lateinit var navigation : TodoTaskNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.root)
        navigation.ActiveScreen = this
    }

    override fun onResume() {
        super.onResume()
        showListUseCase.run()
    }
}