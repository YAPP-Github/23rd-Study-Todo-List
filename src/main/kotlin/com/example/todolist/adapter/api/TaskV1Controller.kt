package com.example.todolist.adapter.api

import com.example.todolist.application.model.Page
import com.example.todolist.application.port.TaskUseCase
import com.example.todolist.domain.Task
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("v1/tasks", produces = [MediaType.APPLICATION_JSON_VALUE])
@RestController
class TaskV1Controller(
    private val taskUseCase: TaskUseCase
) {
    @GetMapping
    fun getTasks(request: PageRequest): ResponseEntity<Page<Task>> {
        val tasks = taskUseCase.getTasks(request.toPageable())
        return ResponseEntity.ok(tasks)
    }

    @GetMapping("{uuid}")
    fun getTask(@PathVariable uuid: UUID): ResponseEntity<Task> {
        val task = taskUseCase.getTask(uuid)
        return ResponseEntity.ok(task)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createTask(
        @RequestBody request: CreateTaskRequest
    ): ResponseEntity<Task> {
        val task = taskUseCase.createTask(request.toCommand())
        return ResponseEntity.status(HttpStatus.CREATED).body(task)
    }

    @PostMapping("bulk", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createTasksInBulk(
        @RequestBody request: CreateTasksInBulkRequest
    ): ResponseEntity<Int> {
        val insertCount = taskUseCase.createTasksInBulk(request.count)
        return ResponseEntity.status(HttpStatus.CREATED).body(insertCount)
    }

    @PatchMapping("{uuid}", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateTask(
        @PathVariable uuid: UUID,
        @RequestBody request: UpdateTaskRequest
    ): ResponseEntity<Task> {
        val task = taskUseCase.updateTask(uuid, request.toCommand())
        return ResponseEntity.ok(task)
    }

    @DeleteMapping("{uuid}")
    fun deleteTask(@PathVariable uuid: UUID): ResponseEntity<Void> {
        taskUseCase.deleteTask(uuid)
        return ResponseEntity.noContent().build()
    }
}