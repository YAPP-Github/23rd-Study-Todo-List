package com.example.todolist.application

import com.example.todolist.application.port.TaskRepository
import com.example.todolist.application.port.TaskUseCase
import com.example.todolist.domain.Task
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TaskService(
    private val taskRepository: TaskRepository
): TaskUseCase {
    override fun getTasks(pageable: Pageable): Page<Task> {
        return taskRepository.findAllOrderByCreatedAtAsc(pageable)
    }

    override fun getTask(uuid: UUID): Task {
        return taskRepository.findByUuidOrNull(uuid) ?: throw TaskNotFoundException()
    }

    override fun createTask(command: CreateTaskCommand): Task {
        val task = Task(command.title, command.description)
        return taskRepository.add(task)
    }

    override fun deleteTask(uuid: UUID) {
        val task = taskRepository.findByUuidOrNull(uuid) ?: throw TaskNotFoundException()
        taskRepository.remove(task)
    }

    override fun updateTask(uuid: UUID, command: UpdateTaskCommand): Task {
        val task = taskRepository.findByUuidOrNull(uuid) ?: throw TaskNotFoundException()
        command.title?.let { task.title = it }
        command.description?.let { task.description = it }
        command.isComplete?.let { task.isComplete = it }
        return task
    }
}