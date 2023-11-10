package yapp.study.todolist.domain.comment.entity

import yapp.study.todolist.domain.base.BaseEntity
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDto

class Comment(
        val id: Long,
        val taskId: Long,
        var content: String
) : BaseEntity() {
    companion object {
        fun toEntity(commentDto: CommentDto): Comment {
            return Comment(
                    id = commentDto.id,
                    taskId = commentDto.taskId,
                    content = commentDto.content
            )
        }
    }

    fun updateContent(contentCommentDto: CommentContentDto) {
        this.content = contentCommentDto.content
    }
}