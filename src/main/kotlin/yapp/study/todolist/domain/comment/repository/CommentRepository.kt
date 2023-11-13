package yapp.study.todolist.domain.comment.repository

import yapp.study.todolist.domain.comment.entity.Comment

interface CommentRepository {
    fun save(comment: Comment): Comment
    fun findById(id: Long): Comment?
    fun deleteById(id: Long)
    fun findByTodoId(id: Long): List<Comment>
    fun deleteAllByTodoId(todoid: Long)
    fun deleteAllByTodoIdIn(todoIds: List<Long>)
    fun existById(id: Long): Boolean
    fun deleteAll()
}