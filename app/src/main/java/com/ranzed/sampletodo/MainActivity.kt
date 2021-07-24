package com.ranzed.sampletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ranzed.sampletodo.domain.usecase.ShowList
import com.ranzed.sampletodo.presentation.ITodoTaskNavigator
import com.ranzed.sampletodo.presentation.TodoTaskNavigation
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ITodoTaskNavigator {

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
        navigation.navigator = this
        showListUseCase.run()
        supportFragmentManager.addOnBackStackChangedListener {
            val count = supportFragmentManager.backStackEntryCount
            if (count == 0)
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
}