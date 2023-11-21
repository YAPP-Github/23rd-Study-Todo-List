package com.example.todolist.adapter

import com.example.todolist.application.port.TaskUseCase
import com.example.todolist.domain.Task
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("v1/tasks")
@RestController
class TaskV1Controller(
    private val taskUseCase: TaskUseCase
) {
    @GetMapping
    fun getTasks(): ResponseEntity<List<Task>> {
        val tasks = taskUseCase.getAllTasks()
        return ResponseEntity.ok(tasks)
    }

    @GetMapping("{uuid}")
    fun getTask(@PathVariable uuid: UUID): ResponseEntity<Task> {
        val task = taskUseCase.getTask(uuid)
        return ResponseEntity.ok(task)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createTask(
        @RequestBody request: CreateTaskRequest
    ): ResponseEntity<Task> {
        val task = taskUseCase.createTask(request.title, request.description)
        return ResponseEntity.status(HttpStatus.CREATED).body(task)
    }

    @PatchMapping("{uuid}", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateTask(
        @PathVariable uuid: UUID,
        @RequestBody request: UpdateTaskRequest
    ): ResponseEntity<Task> {
        val task = taskUseCase.updateTask(uuid, request.title, request.description, request.isComplete)
        return ResponseEntity.ok(task)
    }

    @DeleteMapping("{uuid}")
    fun deleteTask(@PathVariable uuid: UUID): ResponseEntity<Void> {
        taskUseCase.deleteTask(uuid)
        return ResponseEntity.noContent().build()
    }
}