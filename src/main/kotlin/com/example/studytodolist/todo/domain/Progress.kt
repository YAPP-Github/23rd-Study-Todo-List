package com.example.studytodolist.todo.domain

enum class Progress(
    val status: String
) {
    PROCESSING("진행중"),
    COMPLETED("완료"),
    CANCELLED("취소됨"),
}