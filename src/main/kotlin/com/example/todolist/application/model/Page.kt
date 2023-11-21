package com.example.todolist.application.model

data class Pageable(
    val page: Int,
    val size: Int
)

data class Page<T>(
    val pageInfo: PageInfo,
    val pageData: List<T>
)

data class PageInfo(
    val totalSize: Long,
    val totalPages: Int,
    val page: Int,
    val size: Int
) {
    constructor(
        totalSize: Long,
        pageable: Pageable
    ) : this(
        totalSize = totalSize,
        totalPages =
        if (pageable.size == 0) 0
        else (totalSize / pageable.size).toInt() + if (totalSize % pageable.size == 0L) 0 else 1,
        page = pageable.page,
        size = pageable.size
    )
}
