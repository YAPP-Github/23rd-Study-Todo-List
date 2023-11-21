package com.example.todolist.application.port

import com.example.todolist.application.CreateTaskCommand
import com.example.todolist.application.Page
import com.example.todolist.application.Pageable
import com.example.todolist.application.UpdateTaskCommand
import com.example.todolist.domain.Task
import java.util.UUID

interface TaskUseCase {
    fun getTasks(pageable: Pageable): Page<Task>
    fun getTask(uuid: UUID): Task
    fun createTask(command: CreateTaskCommand): Task
    fun deleteTask(uuid: UUID)
    fun updateTask(uuid: UUID, command: UpdateTaskCommand): Task
}