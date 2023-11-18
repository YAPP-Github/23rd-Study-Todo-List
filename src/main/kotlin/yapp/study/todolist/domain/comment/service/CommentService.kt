package yapp.study.todolist.domain.comment.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import yapp.study.todolist.common.error.errors.NotFoundException
import yapp.study.todolist.domain.base.IdGenerator
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDetailDto
import yapp.study.todolist.domain.comment.entity.Comment
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.todo.repository.TodoRepository

@Component
class CommentService(
        private val todoRepository: TodoRepository,
        private val commentRepository: CommentRepository,
) {
    fun createComment(commentDetailDto: CommentDetailDto): Long {
        if(!todoRepository.existsById(commentDetailDto.todoId)){
            throw NotFoundException("not exist todo")
        }
        val comment = commentRepository.save(Comment.toEntity(commentDetailDto))
        return comment.id!!
    }

    fun updateComment(id: Long, commentContentDto: CommentContentDto) {
        commentRepository.findByIdOrNull(id)
                ?.let{
                    it.updateContent(commentContentDto)
                    commentRepository.save(it)
                }
                ?: throw NotFoundException("not exist comment")
    }

    fun deleteComment(id: Long) {
        when (commentRepository.existsById(id)) {
            true -> commentRepository.deleteById(id)
            false -> throw NotFoundException("not exist comment")
        }
    }
}