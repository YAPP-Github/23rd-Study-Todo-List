package com.example.todolist.adapter

import com.example.todolist.application.Pageable

data class PageRequest (
    val page: Int,
    val size: Int
) {
    fun toPageable(): Pageable {
        return Pageable(page, size)
    }
}