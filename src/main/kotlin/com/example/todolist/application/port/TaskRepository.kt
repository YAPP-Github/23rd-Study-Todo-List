package com.example.todolist.application.port

import com.example.todolist.domain.Task
import java.util.UUID

interface TaskRepository {
    fun findAll(): List<Task>
    fun findByUuidOrNull(uuid: UUID): Task?
    fun add(task: Task): Task
    fun remove(task: Task)
}