package yapp.study.todolist.domain.task.service

import org.springframework.stereotype.Service
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.dto.CommentDto
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.task.dto.TaskCommentDto
import yapp.study.todolist.domain.task.dto.TaskDetailDto
import yapp.study.todolist.domain.task.dto.TaskDto
import yapp.study.todolist.domain.task.dto.TasksDto
import yapp.study.todolist.domain.task.entity.Task
import yapp.study.todolist.domain.task.repository.TaskRepository

@Service
class TaskService(
        private val categoryRepository: CategoryRepository,
        private val taskRepository: TaskRepository,
        private val commentRepository: CommentRepository
) {
    fun createTask(taskDto: TaskDto) {
        if (!categoryRepository.existById(taskDto.categoryId)) {
            throw RuntimeException("not exist category")
        }
        taskRepository.findById(taskDto.id)
                ?.let {
                    throw RuntimeException("duplicate task id")
                }
                ?: taskRepository.save(Task.toEntity(taskDto))
    }

    fun getTasks(): TasksDto {
        return TasksDto.toDto(taskRepository.findAll().map {TaskDto.toDto(it)})
    }

    fun updateTask(id: Long, taskDetailDto: TaskDetailDto) {
        if (!categoryRepository.existById(taskDetailDto.categoryId)) {
            throw RuntimeException("not exist category")
        }
        taskRepository.findById(id)
                ?.let {
                    it.update(taskDetailDto)
                    taskRepository.save(it)
                }
                ?: throw RuntimeException("not exist task")
    }

    fun deleteTask(id: Long) {
        taskRepository.findById(id)
                ?.let {
                    taskRepository.deleteById(id)
                }
                ?: throw RuntimeException("not exist task")
        commentRepository.deleteAllByTaskId(id)
    }

    fun updateDoneTask(id: Long, done: Boolean) {
        taskRepository.findById(id)
                ?.let {
                    when (done) {
                        true -> it.doneTask()
                        false -> it.ongoingTask()
                    }
                    taskRepository.save(it)
                }
                ?: throw RuntimeException("not exist task")
    }

    fun getTaskWithComments(id: Long): TaskCommentDto {
        val task = taskRepository.findById(id) ?: throw RuntimeException("not exist task")
        val comments = commentRepository.findByTaskId(id).map { CommentDto.toDto(it) }
        return TaskCommentDto.toDto(task, comments)
    }
}