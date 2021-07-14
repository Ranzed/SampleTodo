package com.ranzed.sampletodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.presentation.view.TodoListAdapter
import java.util.*

class MainActivity : AppCompatActivity() {

    // norm



    // oldschool
    private var rootView : ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rootView = findViewById(R.id.root)
        initView()
    }

    private fun initView() {
        val recyclerView = RecyclerView(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TodoListAdapter()
        adapter.items.addAll(loadTodoItems())
        recyclerView.adapter = adapter
        rootView?.addView(recyclerView)
    }

    private fun loadTodoItems() : Array<TodoTask> {
        return Array(5, { i : Int -> TodoTask(i, i.toString(), null, Date(), false) } )
    }

}