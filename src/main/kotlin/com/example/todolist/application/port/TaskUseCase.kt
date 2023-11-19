package com.example.todolist.application.port

import com.example.todolist.domain.Task
import java.util.UUID

interface TaskUseCase {
    fun getAllTasks(): List<Task>
    fun getTask(uuid: UUID): Task
    fun createTask(title: String, description: String?): Task
    fun deleteTask(uuid: UUID)
    fun updateTask(uuid: UUID, title: String?, description: String?, isComplete: Boolean?): Task
}