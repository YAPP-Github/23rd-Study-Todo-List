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
    override fun getAllTasks(): List<Task> {
        return taskRepository.findAll()
    }

    override fun getTask(uuid: UUID): Task {
        return taskRepository.findByUuid(uuid) ?: throw TaskNotFoundException()
    }

    override fun createTask(title: String, description: String?): Task {
        val task = Task(title, description)
        return taskRepository.add(task)
    }

    override fun deleteTask(uuid: UUID) {
        val task = taskRepository.findByUuid(uuid) ?: throw TaskNotFoundException()
        taskRepository.remove(task)
    }

    override fun updateTask(uuid: UUID, title: String?, description: String?, isComplete: Boolean?): Task {
        val task = taskRepository.findByUuid(uuid) ?: throw TaskNotFoundException()
        title?.let { task.title = it }
        description?.let { task.description = it }
        isComplete?.let { task.isComplete = it }
        return task
    }
}