package yapp.study.todolist.domain.comment.dto

import yapp.study.todolist.domain.comment.entity.Comment

class CommentDto(
        val id: Long,
        val taskId: Long,
        val content: String
) {
    companion object {
        fun toDto(comment: Comment): CommentDto {
            return CommentDto(
                    id = comment.id,
                    taskId = comment.taskId,
                    content = comment.content
            )
        }
    }
}
