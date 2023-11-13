package com.example.studytodolist.todo.dto.response

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo

data class TodoSaveResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val progress: Progress
){
    constructor(todo: Todo): this(todo.id, todo.title, todo.content, todo.progress)
}
