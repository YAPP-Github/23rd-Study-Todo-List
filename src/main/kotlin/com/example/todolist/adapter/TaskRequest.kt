package com.example.todolist.adapter

data class CreateTaskRequest(
    val title: String,
    val description: String?
)

data class UpdateTaskRequest(
    val title: String?,
    val description: String?,
    val isComplete: Boolean?
)
