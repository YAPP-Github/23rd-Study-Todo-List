package yapp.study.todolist.domain.task.dto

import yapp.study.todolist.domain.task.entity.Task
import java.time.LocalDate
import java.time.LocalTime

class TaskDetailDto(
        val categoryId: Long,
        val title: String,
        val memo: String?,
        val date: LocalDate,
        val fromTime: LocalTime?,
        val toTime: LocalTime?)