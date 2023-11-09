package com.example.studytodolist.todo.controller

import com.example.studytodolist.todo.domain.Todo
import com.example.studytodolist.todo.dto.request.TodoSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoUpdateRequestDto
import com.example.studytodolist.todo.dto.response.TodoFindAllResponseDto
import com.example.studytodolist.todo.dto.response.TodoSaveResponseDto
import com.example.studytodolist.todo.dto.response.TodoUpdateResponseDto
import com.example.studytodolist.todo.repository.TodoRepositoryImpl
import com.example.studytodolist.todo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/todo")
class TodoController() {
    private val todoService:TodoService

    init {
        val todoRepositoryImpl = TodoRepositoryImpl()
        this.todoService = TodoService(todoRepositoryImpl)
    }

    @PostMapping("")
    fun save(@RequestBody saveRequest: TodoSaveRequestDto): ResponseEntity<TodoSaveResponseDto> = ResponseEntity.ok(todoService.save(Todo(saveRequest)))

    @GetMapping("/list")
    fun findAll(): ResponseEntity<List<TodoFindAllResponseDto>> = ResponseEntity.ok(todoService.findAll())

    @DeleteMapping("")
    fun delete(@RequestParam title: String): ResponseEntity<Void>{
        todoService.deleteByTitle(title)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("")
    fun update(@RequestBody updateRequest: TodoUpdateRequestDto): ResponseEntity<TodoUpdateResponseDto> = ResponseEntity.ok(todoService.update(updateRequest))
}