package yapp.study.todolist.domain.todo.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import yapp.study.todolist.common.const.BULK_SIZE
import yapp.study.todolist.domain.todo.entity.Todo
import java.sql.Date
import java.sql.Time
import java.time.Instant

@Component
class TodoRepositoryCustomImpl(
        private val jdbcTemplate: JdbcTemplate
) : TodoRepositoryCustom {
    private val todos: MutableMap<Long, Todo> = mutableMapOf()

    override fun saveLocal(todo: Todo): Todo {
        todos[todo.id!!] = todo
        return todo
    }

    override fun findLocalAll(): List<Todo> {
        return todos.values.toList()
    }

    override fun findLocalById(id: Long): Todo? {
        return todos[id]
    }

    override fun deleteLocalById(id: Long) {
        todos.remove(id)
    }

    override fun findLocalByCategoryId(categoryId: Long): List<Todo> {
        return todos.values.filter { it.categoryId == categoryId }
    }

    override fun deleteLocalByIdIn(todoIds: List<Long>) {
        todoIds.map { todos.remove(it) }
    }

    override fun existLocalById(id: Long): Boolean {
        return todos[id] != null
    }

    override fun deleteLocalAll() {
        todos.clear()
    }

    override fun findLocalByIdAndIsDone(id: Long, isDone: Boolean): Todo? {
        return if (todos[id]?.isDone == isDone) {
            todos[id]
        } else
            null
    }

    override fun bulkSave(todos: List<Todo>) {
        val sql = """
            insert into todo(date, from_time, to_time, is_done, created_at, updated_at, memo, title, category_id)
                values (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimIndent()
        jdbcTemplate.batchUpdate(sql, todos, BULK_SIZE) { preparedStatement, todo ->
            preparedStatement.setDate(1, Date.valueOf(todo.date))
            preparedStatement.setTime(2, todo.fromTime ?.let { Time.valueOf(todo.fromTime) })
            preparedStatement.setTime(3, todo.toTime?.let { Time.valueOf(todo.toTime) })
            preparedStatement.setBoolean(4, todo.isDone)
            preparedStatement.setDate(5, Date(Instant.now().toEpochMilli()))
            preparedStatement.setDate(6, Date(Instant.now().toEpochMilli()))
            preparedStatement.setString(7, todo.memo ?.apply {  })
            preparedStatement.setString(8, todo.title)
            preparedStatement.setLong(9, todo.categoryId)
        }
    }
}