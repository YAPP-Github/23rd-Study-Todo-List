package yapp.study.todolist.domain.task.service

import org.springframework.stereotype.Service
import yapp.study.todolist.domain.task.dto.TaskDetailDto
import yapp.study.todolist.domain.task.dto.TaskDto
import yapp.study.todolist.domain.task.dto.TasksDto
import yapp.study.todolist.domain.task.entity.Task
import yapp.study.todolist.domain.task.repository.TaskRepository

@Service
class TaskService(
        private val taskRepository: TaskRepository
) {
    fun createTask(taskDto: TaskDto) {
        taskRepository.save(Task.toEntity(taskDto))
    }

    fun getTasks(): TasksDto {
        return TasksDto.toDto(taskRepository.getTasks().map {TaskDto.toDto(it)})
    }

    fun updateTask(id: Long, taskDetailDto: TaskDetailDto) {
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
}