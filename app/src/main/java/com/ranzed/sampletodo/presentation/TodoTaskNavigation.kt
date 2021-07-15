package com.ranzed.sampletodo.presentation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskNavigation
import javax.inject.Inject

class TodoTaskNavigation @Inject constructor() : ITodoTaskNavigation {

    var ActiveScreen : AppCompatActivity? = null

    override fun showTodoTasksList() {
        // create fragment
        TODO("Not yet implemented")
    }

    override fun showTodoTaskItem(id : Int) {
        // create fragment
        TODO("Not yet implemented")
    }
}