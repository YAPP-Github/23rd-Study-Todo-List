package com.example.studytodolist.todo.repository

import com.example.studytodolist.todo.domain.Todo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TodoRepository: JpaRepository<Todo, Long> {
    override fun findById(id: Long): Optional<Todo>
    override fun deleteById(id: Long)
}