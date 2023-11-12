package yapp.study.todolist.domain.comment.service

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.comment.dto.CommentContentDto
import yapp.study.todolist.domain.comment.dto.CommentDto
import yapp.study.todolist.domain.comment.entity.Comment
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.task.repository.TaskRepository

@Component
class CommentService(
        private val taskRepository: TaskRepository,
        private val commentRepository: CommentRepository
) {
    fun createComment(commentDto: CommentDto) {
        taskRepository.findById(commentDto.taskId) ?: throw RuntimeException("not exist task")
        commentRepository.findById(commentDto.id)
                ?.let { throw RuntimeException("duplicate comment id") }
                ?: commentRepository.save(Comment.toEntity(commentDto))
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