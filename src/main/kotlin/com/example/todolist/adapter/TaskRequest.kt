package com.example.todolist.adapter

import com.example.todolist.application.CreateTaskCommand
import com.example.todolist.application.UpdateTaskCommand

data class CreateTaskRequest(
    val title: String,
    val description: String?
) {
    fun toCommand(): CreateTaskCommand {
        return CreateTaskCommand(title, description)
    }
}

data class UpdateTaskRequest(
    val title: String?,
    val description: String?,
    val isComplete: Boolean?
) {
    fun toCommand(): UpdateTaskCommand {
        return UpdateTaskCommand(title, description, isComplete)
    }
}
