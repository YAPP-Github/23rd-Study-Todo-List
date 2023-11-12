package yapp.study.todolist.domain.comment.repository

import yapp.study.todolist.domain.comment.entity.Comment

interface CommentRepository {
    fun save(comment: Comment)
    fun findById(id: Long): Comment?
    fun deleteById(id: Long)
    fun findByTaskId(id: Long): List<Comment>
    fun deleteAllByTaskId(taskid: Long)
}