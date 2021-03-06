package com.ranzed.sampletodo.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.ranzed.sampletodo.data.local.TodoTaskDB
import com.ranzed.sampletodo.data.local.TodoTaskEntity
import com.ranzed.sampletodo.domain.TodoTask
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * В LocalDataSource конвертим модели из Domain-слоя в сущности БД и обратно для инкапсуляции способа их хранения.
 */
@Singleton
class LocalDataSource @Inject constructor(ctx: Context) {

    private val dbName = "todotask_db"

    private val roomDb: TodoTaskDB = Room.databaseBuilder(
        ctx,
        TodoTaskDB::class.java, dbName
    ).build()

    suspend fun loadAllTodoTasks(): List<TodoTask> =
        roomDb.dao().loadAllTodoTasks()
            .map {
                TodoTask(
                    it.id, it.Title, it.Description, Date(it.Datetime), it.IsDone
                )
            }


    suspend fun getTodoTask(id: Int) =
        roomDb.dao().getTodoTask(id)
            ?.let {
                TodoTask(it.id, it.Title, it.Description, Date(it.Datetime), it.IsDone)
            } ?: TodoTask.createEmpty()


    suspend fun saveTodoTask(t: TodoTask) {
        roomDb.dao()
            .saveTodoTask(TodoTaskEntity(t.id, t.Title, t.Description, t.Datetime.time, t.IsDone))
            .also {
                Log.i("LocalDataSource", "Save todoTask with id = {%s}".format(it))
            }
    }

    suspend fun deleteTodoTask(id: Int) = roomDb.dao().deleteTodoTask(id)
}