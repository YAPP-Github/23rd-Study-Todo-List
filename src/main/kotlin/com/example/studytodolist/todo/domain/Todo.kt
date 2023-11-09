package com.example.studytodolist.todo.domain

import com.example.studytodolist.todo.dto.request.TodoSaveRequestDto

class Todo(
    var title: String,
    var content: String,
    var progress: Progress
) {
    constructor(todoSaveRequestDto: TodoSaveRequestDto) : this(
        todoSaveRequestDto.title,
        todoSaveRequestDto.content,
        todoSaveRequestDto.progress)
}