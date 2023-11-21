package com.example.todolist.adapter

import com.example.todolist.domain.Task
import com.example.todolist.application.port.TaskUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

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

    @PostMapping
    fun createTask(
        @RequestBody request: CreateTaskRequest
    ): ResponseEntity<Task> {
        val task = taskUseCase.createTask(request.title, request.description)
        return ResponseEntity.status(HttpStatus.CREATED).body(task)
    }

    @PatchMapping("{uuid}")
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