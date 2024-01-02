package com.example.studytodolist.todo.dto.response

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo

data class TodoFindResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val progress: Progress,
    val count: Long
){
    constructor(todo: Todo): this(todo.id, todo.title, todo.content, todo.progress, todo.count.get())
}