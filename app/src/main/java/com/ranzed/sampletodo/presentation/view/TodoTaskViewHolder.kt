package com.ranzed.sampletodo.presentation.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.domain.TodoTask

class TodoTaskViewHolder : RecyclerView.ViewHolder {

    private val title : TextView
    private val description : TextView
    private val dateTime : TextView
    private val done : TextView

    constructor(itemView: View) : super(itemView) {
        title = itemView.findViewById(R.id.title)
        description = itemView.findViewById(R.id.description)
        dateTime = itemView.findViewById(R.id.datetime)
        done = itemView.findViewById(R.id.done)
    }

    fun bind(task : TodoTask) {
        title.text = task.Title
        description.text = task.Description
        dateTime.text = task.Datetime.toString() // todo
        done.visibility = if (task.IsDone) View.VISIBLE else View.GONE
    }
}