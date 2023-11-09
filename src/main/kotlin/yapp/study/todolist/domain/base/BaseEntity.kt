package yapp.study.todolist.domain.base

import java.time.LocalDateTime

open class BaseEntity(
        var createdAt: LocalDateTime = LocalDateTime.now(),
        val updatedAt: LocalDateTime = LocalDateTime.now(),
) {
}