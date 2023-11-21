package com.example.todolist.adapter.persistence

import com.example.todolist.domain.Task
import jakarta.persistence.*
import java.time.ZonedDateTime
import java.util.UUID

@Table(name = "task")
@Entity
class TaskEntity {

    @Id
    @Column(nullable = false, unique = true)
    var uuid: UUID = UUID.randomUUID()

    @Column(nullable = false)
    var title: String? = null

    @Column
    var description: String? = null

    @Column(nullable = false)
    var isComplete: Boolean? = false

    @Column(nullable = false)
    var createdAt: ZonedDateTime? = ZonedDateTime.now()

    constructor()

    constructor(task: Task) {
        this.uuid = task.uuid
        this.title = task.title
        this.description = task.description
        this.isComplete = task.isComplete
        this.createdAt = task.createdAt
    }

    fun toDomain(): Task {
        return Task(
            uuid = this.uuid,
            title = this.title!!,
            description = this.description,
            isComplete = this.isComplete!!,
            createdAt = this.createdAt!!
        )
    }
}