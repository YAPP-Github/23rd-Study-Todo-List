package yapp.study.todolist.domain.comment

import yapp.study.todolist.domain.base.BaseEntity

class Comment(
        val id: Long,
        val taskId: Long,
        val content: String
) : BaseEntity()