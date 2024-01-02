package com.example.studytodolist.todo.dto.response

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo

data class TodoUpdateResponseDto(
    val id: Long,
    val title: String,
    val progress: Progress,
    val count: Long
){
    constructor(todo: Todo): this(todo.id, todo.title, todo.progress, todo.count.get())
}