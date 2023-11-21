package com.example.todolist.application.port

import com.example.todolist.application.Page
import com.example.todolist.application.Pageable
import com.example.todolist.domain.Task
import java.util.UUID

interface TaskRepository {
    fun findAllOrderByCreatedAtAsc(pageQuery: Pageable): Page<Task>
    fun findByUuidOrNull(uuid: UUID): Task?
    fun add(task: Task): Task
    fun remove(task: Task)
}