package yapp.study.todolist.domain.comment.repository

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.comment.entity.Comment

@Component
class CommentRepositoryCustomImpl(
        private val comments: MutableMap<Long, Comment> = mutableMapOf()
) : CommentRepositoryCustom {
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

}