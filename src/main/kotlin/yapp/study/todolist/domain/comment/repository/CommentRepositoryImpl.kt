package yapp.study.todolist.domain.comment.repository

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.comment.entity.Comment

@Component
class CommentRepositoryImpl(
        private val comments: MutableMap<Long, Comment> = mutableMapOf()
) : CommentRepository {
    override fun save(comment: Comment) {
        comments[comment.id] = comment
    }

    override fun findById(id: Long): Comment? {
        return comments[id]
    }

    override fun deleteById(id: Long) {
        comments.remove(id)
    }

    override fun findByTaskId(taskId: Long): List<Comment> {
        return comments.values.filter { it.taskId == taskId }
    }

    override fun deleteAllByTaskId(taskid: Long) {
        comments.values.filter { it.taskId == taskid }
                .map { comments.remove(it.id) }
    }

}