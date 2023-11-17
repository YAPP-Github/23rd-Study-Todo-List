package yapp.study.todolist.domain.todo.entity

import jakarta.persistence.*
import yapp.study.todolist.domain.base.BaseEntity
import yapp.study.todolist.domain.todo.dto.TodoDetailDto
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "todo")
class Todo(
        var categoryId: Long,
        var title: String,
        var memo: String?,
        var date: LocalDate,
        var fromTime: LocalTime?,
        var toTime: LocalTime?,
        var isDone: Boolean,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "todo_id")
        val id: Long? = null,
) : BaseEntity() {
    companion object {
        fun toEntity(todoDetailDto: TodoDetailDto): Todo {
            return Todo(
                    categoryId = todoDetailDto.categoryId,
                    title = todoDetailDto.title,
                    memo = todoDetailDto.memo,
                    date = todoDetailDto.date,
                    fromTime = todoDetailDto.fromTime,
                    toTime = todoDetailDto.toTime,
                    isDone = false
            )
        }
    }

    fun update(todoDetailDto: TodoDetailDto) {
        this.categoryId = todoDetailDto.categoryId
        this.title = todoDetailDto.title
        this.memo = todoDetailDto.memo
        this.date = todoDetailDto.date
        this.fromTime = todoDetailDto.fromTime
        this.toTime = todoDetailDto.toTime
    }

    fun doneTodo() {
        this.isDone = true
    }

    fun ongoingTodo() {
        this.isDone = false
    }
}