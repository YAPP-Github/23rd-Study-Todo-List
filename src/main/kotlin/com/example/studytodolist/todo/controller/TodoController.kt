package com.example.studytodolist.todo.controller

import com.example.studytodolist.todo.dto.request.TodoSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoUpdateRequestDto
import com.example.studytodolist.todo.dto.response.TodoFindResponseDto
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
    fun save(@RequestBody saveRequest: TodoSaveRequestDto): ResponseEntity<TodoSaveResponseDto> = ResponseEntity.ok(todoService.save(saveRequest))

    @GetMapping("/list")
    fun findAll(): ResponseEntity<List<TodoFindResponseDto>> = ResponseEntity.ok(todoService.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TodoFindResponseDto> = ResponseEntity.ok(todoService.findById(id))

    @DeleteMapping("")
    fun delete(@RequestParam id: Long): ResponseEntity<Void>{
        todoService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("")
    fun update(@RequestBody updateRequest: TodoUpdateRequestDto): ResponseEntity<TodoUpdateResponseDto> = ResponseEntity.ok(todoService.update(updateRequest))
}