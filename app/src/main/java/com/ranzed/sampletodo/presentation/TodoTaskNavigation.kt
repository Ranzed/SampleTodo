package com.ranzed.sampletodo.presentation

import androidx.appcompat.app.AppCompatActivity
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskNavigation
import com.ranzed.sampletodo.presentation.fragments.TodoTaskListFragment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoTaskNavigation @Inject constructor() : ITodoTaskNavigation {

    var ActiveScreen : AppCompatActivity? = null

    override fun showTodoTasksList() {
        val a = checkNotNull(ActiveScreen) {
            "Need setup active screen"
        }
        val t = a.supportFragmentManager.beginTransaction()
        t.add(R.id.root, TodoTaskListFragment(), "")
        t.commit()
    }

    override fun showTodoTaskItem(id : Int) {
        // create fragment
        TODO("Not yet implemented")
    }
}