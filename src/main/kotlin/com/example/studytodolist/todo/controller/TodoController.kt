package com.example.studytodolist.todo.controller

import com.example.studytodolist.todo.dto.request.BulkSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoSaveRequestDto
import com.example.studytodolist.todo.dto.request.TodoUpdateRequestDto
import com.example.studytodolist.todo.dto.response.BulkSaveResponseDto
import com.example.studytodolist.todo.dto.response.TodoFindResponseDto
import com.example.studytodolist.todo.dto.response.TodoSaveResponseDto
import com.example.studytodolist.todo.dto.response.TodoUpdateResponseDto
import com.example.studytodolist.todo.service.TodoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@Tag(name = "Todo v1.0 API", description = "TodoList 관련 api")
@RestController
@RequestMapping(value = ["/api/v1/todo"], produces = [MediaType.APPLICATION_JSON_VALUE])
class TodoController(private val todoService: TodoService) {
    @Operation(
        summary = "Todo 생성",
        description = "Todo 정보를 받아 생성한다.")
    @PostMapping("")
    fun save(@RequestBody saveRequest: TodoSaveRequestDto): ResponseEntity<TodoSaveResponseDto> {
        val todoSaveResponseDto = todoService.save(saveRequest)
        return ResponseEntity.created(URI.create("/api/v1/todo/".plus(todoSaveResponseDto.id.toString())))
            .body(todoSaveResponseDto)
    }

    @Operation(
        summary = "Todo 리스트 조회",
        description = "모든 Todo를 반환한다.")
    @GetMapping("")
    fun findAll(): ResponseEntity<List<TodoFindResponseDto>> = ResponseEntity.ok(todoService.findAll())

    @Operation(
        summary = "id로 Todo 조회",
        description = "id를 받아 해당하는 Todo를 반환한다.")
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<TodoFindResponseDto> = ResponseEntity.ok(todoService.findById(id))

    @Operation(
        summary = "Todo 삭제",
        description = "id를 받아 해당하는 Todo를 삭제한다.")
    @DeleteMapping("")
    fun delete(@RequestParam id: Long): ResponseEntity<Void>{
        todoService.deleteById(id)
        return ResponseEntity.noContent().build()
    }

    @Operation(
        summary = "Todo progress 업데이트",
        description = "id와 progress를 받아 해당하는 Todo의 progress를 업데이트한다.")
    @PatchMapping("")
    fun update(@RequestBody updateRequest: TodoUpdateRequestDto): ResponseEntity<TodoUpdateResponseDto> =
        ResponseEntity.ok(todoService.update(updateRequest))

    @Operation(
        summary = "Todo bulk 생성",
        description = "count를 받아 해당하는 개수의 mock data를 bulk save 한 후, 생성한 개수를 반환한다.")
    @PostMapping("/bulk")
    fun bulkSave(@RequestBody bulkSaveRequestDto: BulkSaveRequestDto): ResponseEntity<BulkSaveResponseDto>{
        return ResponseEntity.created(URI.create("/api/v1/todo")).body(todoService.bulkSave(bulkSaveRequestDto))
    }
}