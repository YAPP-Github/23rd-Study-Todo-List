package yapp.study.todolist.domain.comment.repository

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.comment.entity.Comment

@Component
class CommentRepositoryImpl(
        private val comments: MutableMap<Long, Comment> = mutableMapOf()
) : CommentRepository {
    override fun save(comment: Comment): Comment {
        comments[comment.id] = comment
        return comment
    }

    override fun findById(id: Long): Comment? {
        return comments[id]
    }

    override fun deleteById(id: Long) {
        comments.remove(id)
    }

    override fun findByTodoId(todoId: Long): List<Comment> {
        return comments.values.filter { it.todoId == todoId }
    }

    override fun deleteAllByTodoId(todoid: Long) {
        comments.values.filter { it.todoId == todoid }
                .map { comments.remove(it.id) }
    }

    override fun deleteAllByTodoIdIn(todoIds: List<Long>) {
        todoIds.map { todoId -> comments.values.filter { it.todoId == todoId }
                    .map { comments.remove(it.id) }}
    }

    override fun existById(id: Long): Boolean {
        return comments[id] != null
    }

    override fun deleteAll() {
        comments.clear()
    }

}