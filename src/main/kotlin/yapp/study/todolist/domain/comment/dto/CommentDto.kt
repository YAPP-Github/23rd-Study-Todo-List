package yapp.study.todolist.domain.comment.dto

import yapp.study.todolist.domain.comment.entity.Comment

class CommentDto(
        val id: Long,
        val todoId: Long,
        val content: String
) {
    companion object {
        fun toDto(comment: Comment): CommentDto {
            return CommentDto(
                    id = comment.id!!,
                    todoId = comment.todoId,
                    content = comment.content
            )
        }
    }
}
