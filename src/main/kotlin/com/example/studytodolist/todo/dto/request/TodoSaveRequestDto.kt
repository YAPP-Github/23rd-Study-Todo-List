package com.example.studytodolist.todo.dto.request

import com.example.studytodolist.todo.domain.Progress

data class TodoSaveRequestDto(
    val title: String,
    val content: String,
    val progress: Progress
)
