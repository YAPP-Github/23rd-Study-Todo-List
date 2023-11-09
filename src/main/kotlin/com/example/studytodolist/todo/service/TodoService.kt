package com.example.studytodolist.todo.service

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo
import com.example.studytodolist.todo.repository.TodoRepositoryImpl

class TodoService(
    private val todoRepositoryImpl:TodoRepositoryImpl
) {
    fun save(todo: Todo): Todo{
        return todoRepositoryImpl.save(todo)
    }
    fun findAll(): List<Todo>{
        return todoRepositoryImpl.findAll()
    }
    fun deleteByTitle(title: String){
        todoRepositoryImpl.deleteByTitle(title)
    }
    fun update(title: String, progressString: String): Todo{
        val progress: Progress = when(progressString){
            "PROCESSING" -> Progress.PROCESSING
            "CANCELLED" -> Progress.CANCELLED
            "COMPLETED" -> Progress.COMPLETED
            else -> Progress.PROCESSING
        }
        return todoRepositoryImpl.updateProgress(title, progress)
    }
}