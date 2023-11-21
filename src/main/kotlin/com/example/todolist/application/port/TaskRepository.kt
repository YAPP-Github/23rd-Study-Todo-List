package com.example.todolist.application.port

import com.example.todolist.application.model.Page
import com.example.todolist.application.model.Pageable
import com.example.todolist.domain.Task
import java.util.UUID

interface TaskRepository {
    fun findAllOrderByCreatedAtAsc(pageable: Pageable): Page<Task>
    fun findByUuidOrNull(uuid: UUID): Task?
    fun save(task: Task): Task
    fun delete(task: Task)
}