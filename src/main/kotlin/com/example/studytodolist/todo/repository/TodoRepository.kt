package com.example.studytodolist.todo.repository

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo

interface TodoRepository {
    fun save(todo: Todo): Todo
    fun findAll(): List<Todo>
    fun findByIdOrNull(id: Long): Todo?
    fun deleteById(id: Long)
    fun updateProgress(id: Long, progress: Progress): Todo
}