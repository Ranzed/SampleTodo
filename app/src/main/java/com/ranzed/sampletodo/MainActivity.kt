package com.ranzed.sampletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.ranzed.sampletodo.domain.usecase.ShowList
import com.ranzed.sampletodo.presentation.ITodoTaskNavigator
import com.ranzed.sampletodo.presentation.TodoTaskNavigation
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ITodoTaskNavigator {

    @Inject
    lateinit var showListUseCase: ShowList

    @Inject
    lateinit var navigation: TodoTaskNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        navigation.navigator = this
        showListUseCase.run() // todo save running state in savedInstanceState
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0)
                finish()
        }
    }

    override fun pushFragment(f: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.root, f)
            .addToBackStack(supportFragmentManager.backStackEntryCount.toString())
            .commit()
    }

    override fun popFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun showSnackbar(resId: Int) {
        Snackbar.make(findViewById(R.id.root), resId, Snackbar.LENGTH_SHORT).show()
    }
}