package com.example.studytodolist.todo.dto.request

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo

data class TodoSaveRequestDto(
    val title: String,
    val content: String,
    val progress: Progress
){
    companion object{
        fun toEntity(todoSaveRequestDto: TodoSaveRequestDto): Todo{
            return Todo(title = todoSaveRequestDto.title, content = todoSaveRequestDto.content, progress = todoSaveRequestDto.progress)
        }
    }
}
