package yapp.study.todolist.domain.comment.service

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDto
import yapp.study.todolist.domain.comment.entity.Comment
import yapp.study.todolist.domain.comment.repository.CommentRepository

@Component
class CommentService(
        private val commentRepository: CommentRepository
) {
    fun createComment(commentDto: CommentDto) {
        commentRepository.save(Comment.toEntity(commentDto))
    }

    fun updateComment(id: Long, commentContentDto: CommentContentDto) {
        commentRepository.findById(id)
                ?.let{
                    it.updateContent(commentContentDto)
                    commentRepository.save(it)
                }
                ?: throw RuntimeException("not exist comment")
    }

    fun deleteComment(id: Long) {
        commentRepository.findById(id)
                ?.let {
                    commentRepository.deleteById(id)
                }
                ?: throw RuntimeException("not exist comment")
    }

}