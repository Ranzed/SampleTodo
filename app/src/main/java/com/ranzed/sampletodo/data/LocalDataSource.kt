
import android.content.Context
import androidx.room.Room
import com.ranzed.sampletodo.data.local.TodoTaskDB
import com.ranzed.sampletodo.data.local.TodoTaskEntity
import com.ranzed.sampletodo.domain.TodoTask
import javax.inject.Inject
import javax.inject.Singleton

/**
 * В LocalDataSource конвертим модели из Domain-слоя в сущности БД и обратно для инкапсуляции способа их хранения.
 */
@Singleton
class LocalDataSource @Inject constructor(ctx : Context) {

    private val dbName = "todotask_db"

    private val roomDb : TodoTaskDB = Room.databaseBuilder(
        ctx,
        TodoTaskDB::class.java, dbName
    ).build()

    fun loadAllTodoTasks() : List<TodoTask> {
        val taskEntities = roomDb.dao().loadAllTodoTasks()
        return taskEntities.map { TodoTask(
                it.id, it.Title, it.Description, it.Datetime, it.IsDone) }
    }

    fun getTodoTask(id : Int) : TodoTask {
        val t = roomDb.dao().getTodoTask(id)
        return TodoTask(t.id, t.Title, t.Description, t.Datetime, t.IsDone)
    }

    fun saveTodoTask(t : TodoTask) {
        roomDb.dao().saveTodoTask(TodoTaskEntity(t.id, t.Title, t.Description, t.Datetime, t.IsDone))
    }

    fun deleteTodoTask(id : Int) {
        roomDb.dao().deleteTodoTask(id)
    }
}