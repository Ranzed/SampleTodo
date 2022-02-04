package com.ranzed.sampletodo.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.domain.TodoTask
import java.util.ArrayList

class TodoListAdapter(private val listItemClickListener: ListItemClickListener) :
    RecyclerView.Adapter<TodoTaskViewHolder>() {

    private val items: MutableList<TodoTask> = ArrayList()

    fun setupList(todoTasks: List<TodoTask>) {
        items.clear()
        items.addAll(todoTasks)
        notifyDataSetChanged()
        // TODO use diffUtils
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoTaskViewHolder {
        return TodoTaskViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todotaskitem, parent, false), listItemClickListener
        )
    }

    override fun onBindViewHolder(holder: TodoTaskViewHolder, position: Int) {
        if (position < items.size)
            holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

fun interface ListItemClickListener {
    fun onListItemClick(id: Int)
}