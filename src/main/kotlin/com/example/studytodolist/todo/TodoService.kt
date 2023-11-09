package com.example.studytodolist.todo

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
    fun update(title: String, progress: Progress): Todo{
        return todoRepositoryImpl.updateProgress(title, progress)
    }
}