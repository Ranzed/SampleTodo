package com.ranzed.sampletodo.presentation.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.presentation.format

class TodoTaskViewHolder(itemView: View, private val clickListener: ListItemClickListener) : RecyclerView.ViewHolder(itemView){

    private val title : TextView = itemView.findViewById(R.id.title)
    private val description : TextView = itemView.findViewById(R.id.description)
    private val dateTime : TextView = itemView.findViewById(R.id.datetime)
    private val done : TextView = itemView.findViewById(R.id.done)
    private var todoTaskId : Int = 0

    init {
        itemView.setOnClickListener { clickListener.onListItemClick(todoTaskId) }
    }

    fun bind(task : TodoTask) {
        todoTaskId = task.id
        title.text = task.Title
        description.text = task.Description
        dateTime.text = task.Datetime.format()
        done.visibility = if (task.IsDone) View.VISIBLE else View.GONE
    }
}