package com.example.studytodolist.todo.domain

import com.fasterxml.jackson.annotation.JsonProperty

enum class Progress(
    val status: String
) {
    @JsonProperty("Processing")
    PROCESSING("진행중"),
    @JsonProperty("Completed")
    COMPLETED("완료"),
    @JsonProperty("Cancelled")
    CANCELLED("취소됨"),
}