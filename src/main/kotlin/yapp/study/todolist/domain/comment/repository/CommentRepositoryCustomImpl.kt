package yapp.study.todolist.domain.comment.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import yapp.study.todolist.common.const.BULK_SIZE
import yapp.study.todolist.domain.comment.entity.Comment
import java.sql.Date
import java.time.Instant

@Component
class CommentRepositoryCustomImpl(
        private val jdbcTemplate: JdbcTemplate
) : CommentRepositoryCustom {
    private val comments: MutableMap<Long, Comment> = mutableMapOf()

    override fun saveLocal(comment: Comment): Comment {
        comments[comment.id!!] = comment
        return comment
    }

    override fun findLocalById(id: Long): Comment? {
        return comments[id]
    }

    override fun deleteLocalById(id: Long) {
        comments.remove(id)
    }

    override fun findLocalByTodoId(todoId: Long): List<Comment> {
        return comments.values.filter { it.todoId == todoId }
    }

    override fun deleteLocalAllByTodoId(todoid: Long) {
        comments.values.filter { it.todoId == todoid }
                .map { comments.remove(it.id) }
    }

    override fun deleteLocalAllByTodoIdIn(todoIds: List<Long>) {
        todoIds.map { todoId -> comments.values.filter { it.todoId == todoId }
                    .map { comments.remove(it.id) }}
    }

    override fun existLocalById(id: Long): Boolean {
        return comments[id] != null
    }

    override fun deleteLocalAll() {
        comments.clear()
    }

    override fun bulkSave(comments: List<Comment>) {
        val sql = """
            insert into comment(todo_id, content, created_at, updated_at)
                values (?, ?, ?, ?)
        """.trimIndent()
        jdbcTemplate.batchUpdate(sql, comments, BULK_SIZE) { preparedStatement, comment ->
            preparedStatement.setLong(1, comment.todoId)
            preparedStatement.setString(2, comment.content)
            preparedStatement.setDate(3, Date(Instant.now().toEpochMilli()))
            preparedStatement.setDate(4, Date(Instant.now().toEpochMilli()))
        }
    }

}