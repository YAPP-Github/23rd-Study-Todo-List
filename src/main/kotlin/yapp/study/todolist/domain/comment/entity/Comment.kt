package yapp.study.todolist.domain.comment.entity

import jakarta.persistence.*
import yapp.study.todolist.domain.base.BaseEntity
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDetailDto
import yapp.study.todolist.domain.comment.dto.CommentDto

@Entity
@Table(name = "comment")
class Comment(
        val todoId: Long,
        var content: String,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "comment_id")
        val id: Long? = null,
) : BaseEntity() {
    companion object {
        fun toEntity(commentDetailDto: CommentDetailDto): Comment {
            return Comment(
                    todoId = commentDetailDto.todoId,
                    content = commentDetailDto.content
            )
        }
    }

    fun updateContent(contentCommentDto: CommentContentDto) {
        this.content = contentCommentDto.content
    }
}