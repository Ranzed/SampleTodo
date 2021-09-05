package com.ranzed.sampletodo.presentation.view

import android.graphics.Paint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ranzed.sampletodo.R
import com.ranzed.sampletodo.domain.TodoTask
import com.ranzed.sampletodo.presentation.format

class TodoTaskViewHolder(itemView: View, private val clickListener: ListItemClickListener) :
    RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.title)
    private val description: TextView = itemView.findViewById(R.id.description)
    private val dateTime: TextView = itemView.findViewById(R.id.datetime)
    private val done: TextView = itemView.findViewById(R.id.done)
    private var todoTaskId: Int = 0

    init {
        itemView.setOnClickListener { clickListener.onListItemClick(todoTaskId) }
    }

    fun bind(task: TodoTask) {
        todoTaskId = task.id
        title.text = task.Title
        description.text = task.Description
        dateTime.text = task.Datetime.format()
        if (task.IsDone) styleIsDone() else styleIsNotDone()
    }

    private fun styleIsDone() {
        title.paintFlags = title.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        description.maxLines = 1
        done.visibility = View.VISIBLE
    }

    private fun styleIsNotDone() {
        title.paintFlags = title.paintFlags and (Paint.STRIKE_THRU_TEXT_FLAG xor Int.MAX_VALUE)
        description.maxLines = 5
        done.visibility = View.GONE
    }
}