package com.example.todolist.application.model

data class CreateTaskCommand(
    val title: String,
    val description: String?
)

data class UpdateTaskCommand(
    val title: String?,
    val description: String?,
    val isComplete: Boolean?
)
