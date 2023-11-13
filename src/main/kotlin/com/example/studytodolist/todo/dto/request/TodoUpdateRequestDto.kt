package com.example.studytodolist.todo.dto.request

import com.example.studytodolist.todo.domain.Progress

data class TodoUpdateRequestDto(
    val id: Long,
    val progress: Progress
)
