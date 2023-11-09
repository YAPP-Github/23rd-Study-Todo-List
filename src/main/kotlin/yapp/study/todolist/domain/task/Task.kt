package yapp.study.todolist.domain.task

import yapp.study.todolist.domain.base.BaseEntity
import java.time.LocalDate
import java.time.LocalTime

class Task(
        val id: Long,
        val categoryId: Long,
        val title: String,
        val memo: String?,
        val date: LocalDate,
        val fromTime: LocalTime?,
        val toTime: LocalDate?
) : BaseEntity()