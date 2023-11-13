package com.example.studytodolist.todo.service

import com.example.studytodolist.todo.domain.Todo
import com.example.studytodolist.todo.dto.request.TodoSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoUpdateRequestDto
import com.example.studytodolist.todo.dto.response.TodoFindResponseDto
import com.example.studytodolist.todo.dto.response.TodoSaveResponseDto
import com.example.studytodolist.todo.dto.response.TodoUpdateResponseDto
import com.example.studytodolist.todo.repository.TodoRepositoryImpl
import org.springframework.stereotype.Component

@Component
class TodoService(
    private val todoRepositoryImpl:TodoRepositoryImpl
) {

    fun save(todoSaveRequestDto: TodoSaveRequestDto): TodoSaveResponseDto{
        val todo = Todo(TodoRepositoryImpl.INDEX++,
        todoSaveRequestDto.title,
        todoSaveRequestDto.content,
        todoSaveRequestDto.progress)
        return TodoSaveResponseDto(todoRepositoryImpl.save(todo))
    }

    fun findAll(): List<TodoFindResponseDto>{
        return todoRepositoryImpl.findAll().map{TodoFindResponseDto(it)}.toList()
    }

    fun findById(id: Long): TodoFindResponseDto {
        val todo = todoRepositoryImpl.findById(id) ?: throw RuntimeException("존재하지 않는 todo입니다")
        return TodoFindResponseDto(todo)
    }

    fun deleteById(id: Long) = todoRepositoryImpl.deleteById(id)

    fun update(todoUpdateRequestDto: TodoUpdateRequestDto): TodoUpdateResponseDto{
        return TodoUpdateResponseDto(todoRepositoryImpl.updateProgress(todoUpdateRequestDto.id, todoUpdateRequestDto.progress))
    }
}