package yapp.study.todolist.domain.todo.dto

import java.time.LocalDate
import java.time.LocalTime

class TodoDetailDto(
        val categoryId: Long,
        val title: String,
        val memo: String?,
        val date: LocalDate,
        val fromTime: LocalTime?,
        val toTime: LocalTime?)