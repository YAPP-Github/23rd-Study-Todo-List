package yapp.study.todolist.domain.comment.entity

import yapp.study.todolist.domain.base.BaseEntity
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDetailDto
import yapp.study.todolist.domain.comment.dto.CommentDto

class Comment(
        val id: Long,
        val todoId: Long,
        var content: String
) : BaseEntity() {
    companion object {
        fun toEntity(id: Long, commentDetailDto: CommentDetailDto): Comment {
            return Comment(
                    id = id,
                    todoId = commentDetailDto.todoId,
                    content = commentDetailDto.content
            )
        }
    }

    fun updateContent(contentCommentDto: CommentContentDto) {
        this.content = contentCommentDto.content
    }
}