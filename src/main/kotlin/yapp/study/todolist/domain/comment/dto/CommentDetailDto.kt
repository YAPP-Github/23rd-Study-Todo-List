package yapp.study.todolist.domain.comment.dto

import yapp.study.todolist.domain.comment.entity.Comment

class CommentDetailDto(
        val taskId: Long,
        val content: String
)
