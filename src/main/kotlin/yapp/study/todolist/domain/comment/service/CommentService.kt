package yapp.study.todolist.domain.comment.service

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
        private val idGenerator: IdGenerator
) {
    fun createComment(commentDetailDto: CommentDetailDto): Long {
        if(!todoRepository.existById(commentDetailDto.todoId)){
            throw NotFoundException("not exist todo")
        }
        val generatedId = idGenerator.getAndIncreaseCommentId()
        val comment = Comment.toEntity(generatedId, commentDetailDto)
        commentRepository.save(comment)
        return generatedId
    }

    fun updateComment(id: Long, commentContentDto: CommentContentDto) {
        commentRepository.findById(id)
                ?.let{
                    it.updateContent(commentContentDto)
                    commentRepository.save(it)
                }
                ?: throw NotFoundException("not exist comment")
    }

    fun deleteComment(id: Long) {
        when (commentRepository.existById(id)) {
            true -> commentRepository.deleteById(id)
            false -> throw NotFoundException("not exist comment")
        }
    }
}