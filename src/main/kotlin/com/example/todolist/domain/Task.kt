package com.example.todolist.domain

import java.util.UUID

data class Task(
    val uuid: UUID,
    var title: String,
    var description: String?,
    var isComplete: Boolean
) {
    constructor(title: String, description: String?): this(
        uuid = UUID.randomUUID(),
        title = title,
        description = description,
        isComplete = false
    )
}