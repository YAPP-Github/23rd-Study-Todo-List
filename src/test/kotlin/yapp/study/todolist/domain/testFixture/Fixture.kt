package yapp.study.todolist.domain.testFixture

import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.task.entity.Task
import java.time.LocalDate
import java.time.LocalTime

object Fixture {
    fun createCategory(
            id: Long = 1,
            name: String = "1111"
    ): Category {
        return Category(
                id = id,
                name = name
        )
    }

    fun createTask(
            id: Long = 1,
            categoryId: Long = 1,
            title: String = "1",
            memo: String = "11",
            date: LocalDate = LocalDate.MIN,
            fromTime: LocalTime = LocalTime.MIN,
            toTime: LocalTime = LocalTime.MAX,
            isDone: Boolean = false
    ): Task {
        return Task(
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
}