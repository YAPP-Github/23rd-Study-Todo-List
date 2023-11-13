package yapp.study.todolist.domain.comment.repository

import yapp.study.todolist.domain.comment.entity.Comment

interface CommentRepository {
    fun save(comment: Comment): Comment
    fun findById(id: Long): Comment?
    fun deleteById(id: Long)
    fun findByTaskId(id: Long): List<Comment>
    fun deleteAllByTaskId(taskid: Long)
    fun deleteAllByTaskIdIn(taskIds: List<Long>)
    fun existById(id: Long): Boolean
    fun deleteAll()
}