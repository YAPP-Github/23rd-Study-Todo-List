package yapp.study.todolist.domain.task.entity

import yapp.study.todolist.domain.base.BaseEntity
import yapp.study.todolist.domain.task.dto.TaskDetailDto
import yapp.study.todolist.domain.task.dto.TaskDto
import java.time.LocalDate
import java.time.LocalTime

class Task(
        val id: Long,
        var categoryId: Long,
        var title: String,
        var memo: String?,
        var date: LocalDate,
        var fromTime: LocalTime?,
        var toTime: LocalTime?,
        var isDone: Boolean
) : BaseEntity() {
    companion object {
        fun toEntity(id: Long, taskDetailDto: TaskDetailDto): Task {
            return Task(
                    id = id,
                    categoryId = taskDetailDto.categoryId,
                    title = taskDetailDto.title,
                    memo = taskDetailDto.memo,
                    date = taskDetailDto.date,
                    fromTime = taskDetailDto.fromTime,
                    toTime = taskDetailDto.toTime,
                    isDone = false
            )
        }
    }

    fun update(taskDetailDto: TaskDetailDto) {
        this.categoryId = taskDetailDto.categoryId
        this.title = taskDetailDto.title
        this.memo = taskDetailDto.memo
        this.date = taskDetailDto.date
        this.fromTime = taskDetailDto.fromTime
        this.toTime = taskDetailDto.toTime
    }

    fun doneTask() {
        this.isDone = true
    }

    fun ongoingTask() {
        this.isDone = false
    }
}