package com.example.studytodolist.todo.service

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo
import com.example.studytodolist.todo.dto.request.TodoUpdateRequestDto
import com.example.studytodolist.todo.dto.response.TodoFindAllResponseDto
import com.example.studytodolist.todo.dto.response.TodoSaveResponseDto
import com.example.studytodolist.todo.dto.response.TodoUpdateResponseDto
import com.example.studytodolist.todo.repository.TodoRepositoryImpl
import org.springframework.http.ResponseEntity

class TodoService(
    private val todoRepositoryImpl:TodoRepositoryImpl
) {
    fun save(todo: Todo): TodoSaveResponseDto{
        return TodoSaveResponseDto(todoRepositoryImpl.save(todo))
    }
    fun findAll(): List<TodoFindAllResponseDto>{
        return todoRepositoryImpl.findAll().map{TodoFindAllResponseDto(it)}.toList()
    }
    fun deleteByTitle(title: String) = todoRepositoryImpl.deleteByTitle(title)
    fun update(todoUpdateRequestDto: TodoUpdateRequestDto): TodoUpdateResponseDto{
        return TodoUpdateResponseDto(todoRepositoryImpl.updateProgress(todoUpdateRequestDto.title, todoUpdateRequestDto.progress))
    }
}