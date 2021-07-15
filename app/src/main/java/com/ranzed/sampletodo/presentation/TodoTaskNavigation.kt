package com.ranzed.sampletodo.presentation

import androidx.fragment.app.Fragment
import com.ranzed.sampletodo.domain.interfaces.ITodoTaskNavigation
import com.ranzed.sampletodo.presentation.fragments.TodoTaskDetailFragment
import com.ranzed.sampletodo.presentation.fragments.TodoTaskListFragment
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Простейшая навигация, отображающая фрагменты в переданном извне навигаторе, подобие "мини-Cicerone"
 */
@Singleton
class TodoTaskNavigation @Inject constructor() : ITodoTaskNavigation {

    var navigator : ITodoTaskNavigator? = null

    override fun showTodoTasksList() {
        val n = checkNotNull(navigator) {
            "Need setup navigator screen"
        }
        n.showFragment(TodoTaskListFragment())
    }

    override fun showTodoTaskItem(id : Int) {
        val n = checkNotNull(navigator) {
            "Need setup navigator screen"
        }
        n.showFragment(TodoTaskDetailFragment())
    }
}

interface ITodoTaskNavigator {
    fun showFragment(f : Fragment)
}