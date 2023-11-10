package yapp.study.todolist.domain.task.dto

import org.springframework.format.annotation.DateTimeFormat
import yapp.study.todolist.domain.task.entity.Task
import java.time.LocalDate
import java.time.LocalTime

class TaskDto(
        val id: Long,
        val categoryId: Long,
        val title: String,
        val memo: String?,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val date: LocalDate,
        @DateTimeFormat(pattern = "HH:mm:ss")
        val fromTime: LocalTime?,
        @DateTimeFormat(pattern = "HH:mm:ss")
        val toTime: LocalTime?,
        val isDone: Boolean) {
    companion object {
        fun toDto(task: Task): TaskDto {
            return TaskDto(
                    id = task.id,
                    categoryId = task.categoryId,
                    title = task.title,
                    memo = task.memo,
                    date = task.date,
                    fromTime = task.fromTime,
                    toTime = task.toTime,
                    isDone = task.isDone
            )
        }
    }

}