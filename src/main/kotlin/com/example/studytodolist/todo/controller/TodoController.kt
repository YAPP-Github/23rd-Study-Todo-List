package com.example.studytodolist.todo.controller

import com.example.studytodolist.todo.dto.request.TodoSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoUpdateRequestDto
import com.example.studytodolist.todo.dto.response.TodoFindResponseDto
import com.example.studytodolist.todo.dto.response.TodoSaveResponseDto
import com.example.studytodolist.todo.dto.response.TodoUpdateResponseDto
import com.example.studytodolist.todo.service.TodoService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping(value = ["/api/v1/todo"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TodoController(private val todoService: TodoService) {
    @PostMapping("")
    fun save(@RequestBody saveRequest: TodoSaveRequestDto): ResponseEntity<TodoSaveResponseDto> {
        val todoSaveResponseDto = todoService.save(saveRequest)
        return ResponseEntity.created(URI.create("/api/v1/todo/".plus(todoSaveResponseDto.id.toString())))
            .body(todoSaveResponseDto)
    }

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