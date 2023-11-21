package com.example.todolist.adapter.api

import com.example.todolist.application.model.Pageable

data class PageRequest (
    val page: Int,
    val size: Int
) {
    fun toPageable(): Pageable {
        return Pageable(page, size)
    }
}