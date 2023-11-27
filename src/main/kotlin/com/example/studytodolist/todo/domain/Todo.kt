package com.example.studytodolist.todo.domain

import jakarta.persistence.*
import java.util.concurrent.atomic.AtomicLong

@Entity
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    var title: String,
    var content: String,
    @Enumerated(EnumType.STRING)
    var progress: Progress,
    var count: AtomicLong = AtomicLong(0)
)