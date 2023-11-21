package com.example.todolist.application.port

import com.example.todolist.application.model.CreateTaskCommand
import com.example.todolist.application.model.Page
import com.example.todolist.application.model.Pageable
import com.example.todolist.application.model.UpdateTaskCommand
import com.example.todolist.domain.Task
import java.util.UUID

interface TaskUseCase {
    fun getTasks(pageable: Pageable): Page<Task>
    fun getTask(uuid: UUID): Task
    fun createTask(command: CreateTaskCommand): Task
    fun createTasksInBulk(count: Int): Int
    fun deleteTask(uuid: UUID)
    fun updateTask(uuid: UUID, command: UpdateTaskCommand): Task
}