package com.example.studytodolist.todo.dto.request

import com.example.studytodolist.todo.domain.Progress

data class TodoUpdateRequestDto(
    val title: String,
    val progress: Progress
)
