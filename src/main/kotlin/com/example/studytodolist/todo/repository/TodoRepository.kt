package com.example.studytodolist.todo.repository

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo

interface TodoRepository {
    fun save(todo: Todo): Todo
    fun findAll(): List<Todo>
    fun deleteByTitle(title: String)
    fun updateProgress(title: String, progress: Progress): Todo
}