package com.example.studytodolist.todo.domain

import jakarta.persistence.*

@Entity
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0L,
    var title: String,
    var content: String,
    @Enumerated(EnumType.STRING)
    var progress: Progress
)