package yapp.study.todolist.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import yapp.study.todolist.domain.comment.entity.Comment

interface CommentRepository : JpaRepository<Comment, Long>, CommentRepositoryCustom {
    fun deleteAllByTodoIdIn(todoIds: List<Long>)
    fun deleteAllByTodoId(todoid: Long)
    fun findAllByTodoId(id: Long): List<Comment>
}

interface CommentRepositoryCustom {
    fun saveLocal(comment: Comment): Comment
    fun findLocalById(id: Long): Comment?
    fun deleteLocalById(id: Long)
    fun findLocalByTodoId(id: Long): List<Comment>
    fun deleteLocalAllByTodoId(todoid: Long)
    fun deleteLocalAllByTodoIdIn(todoIds: List<Long>)
    fun existLocalById(id: Long): Boolean
    fun deleteLocalAll()
}