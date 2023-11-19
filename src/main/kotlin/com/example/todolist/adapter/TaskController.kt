package com.example.todolist.adapter

import com.example.todolist.domain.Task
import com.example.todolist.application.port.TaskUseCase
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RequestMapping("tasks")
@RestController
class TaskController(
    private val taskUseCase: TaskUseCase
) {

    @GetMapping
    fun getTasks(): List<Task> {
        return taskUseCase.getAllTasks()
    }

    @GetMapping("{uuid}")
    fun getTask(@PathVariable uuid: UUID): Task {
        return taskUseCase.getTask(uuid)
    }

    @PostMapping
    fun createTask(
        @RequestBody request: CreateTaskRequest
    ): Task {
        return taskUseCase.createTask(request.title, request.description)
    }

    @PatchMapping("{uuid}")
    fun updateTask(
        @PathVariable uuid: UUID,
        @RequestBody request: UpdateTaskRequest
    ): Task {
        return taskUseCase.updateTask(uuid, request.title, request.description, request.isComplete)
    }

    @DeleteMapping("{uuid}")
    fun deleteTask(@PathVariable uuid: UUID) {
        taskUseCase.deleteTask(uuid)
    }
}