package com.ranzed.sampletodo.presentation

import android.os.Bundle
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
        val n = checkNotNull(navigator) { "Need setup navigator screen" }
        n.pushFragment(TodoTaskListFragment())
    }

    override fun showTodoTaskItem(id : Int) {
        val n = checkNotNull(navigator) { "Need setup navigator screen" }
        n.pushFragment(TodoTaskDetailFragment().also {
            it.arguments = Bundle().also {
                    b -> b.putInt(TodoTaskDetailFragment.id_key, id) } })
    }

    override fun showPreviousPage() {
        val n = checkNotNull(navigator) { "Need setup navigator screen" }
        n.popFragment()
    }

    override fun showSnackbar(resId : Int) {
        val n = checkNotNull(navigator) { "Need setup navigator screen" }
        n.showSnackbar(resId)
    }
}

interface ITodoTaskNavigator {
    fun pushFragment(f : Fragment)
    fun popFragment()
    fun showSnackbar(resId : Int)
}