package yapp.study.todolist.domain.testFixture

import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.comment.entity.Comment
import yapp.study.todolist.domain.todo.entity.Todo
import java.time.LocalDate
import java.time.LocalTime

object Fixture {
    const val categoryId: Long = 1
    const val todoId: Long = 1
    const val commentId: Long = 1

    fun createCategory(
            id: Long = 1,
            name: String = "1111"
    ): Category {
        return Category(
                id = id,
                name = name
        )
    }

    fun createTodo(
            id: Long = 1,
            categoryId: Long = 1,
            title: String = "1",
            memo: String = "11",
            date: LocalDate = LocalDate.MIN,
            fromTime: LocalTime = LocalTime.MIN,
            toTime: LocalTime = LocalTime.MAX,
            isDone: Boolean = false
    ): Todo {
        return Todo(
                id = id,
                categoryId = categoryId,
                title = title,
                memo = memo,
                date = date,
                fromTime = fromTime,
                toTime = toTime,
                isDone = isDone
        )
    }

    fun createComment(
            id: Long = 1,
            todoId: Long = 1,
            content: String = "11"
    ): Comment {
        return Comment(
                id = id,
                todoId = todoId,
                content = content
        )
    }
}