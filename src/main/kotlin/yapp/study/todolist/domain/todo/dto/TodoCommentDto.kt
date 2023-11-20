package yapp.study.todolist.domain.todo.dto

import org.springframework.format.annotation.DateTimeFormat
import yapp.study.todolist.domain.comment.dto.CommentDto
import yapp.study.todolist.domain.todo.entity.Todo
import java.time.LocalDate
import java.time.LocalTime

class TodoCommentDto(
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
        val isDone: Boolean,
        val comments: List<CommentDto>
) {
    companion object {
        fun toDto(todo: Todo, comments: List<CommentDto>): TodoCommentDto {
            return TodoCommentDto(
                    id = todo.id,
                    categoryId = todo.categoryId,
                    title = todo.title,
                    memo = todo.memo,
                    date = todo.date,
                    fromTime = todo.fromTime,
                    toTime = todo.toTime,
                    isDone = todo.isDone,
                    comments = comments
            )
        }
    }
}