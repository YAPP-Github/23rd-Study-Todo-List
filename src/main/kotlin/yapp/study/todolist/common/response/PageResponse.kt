package yapp.study.todolist.common.response

import org.springframework.data.domain.Page

class PageResponse<T>(
        val content: List<T>,
        val page: Int,
        val size: Int,
        val totalElements: Long,
        val totalPages: Int,
        val hasNextPage: Boolean,
) {
    companion object {
        fun <T> toResponse(page: Page<T>): PageResponse<T> {
            return PageResponse(
                    content = page.content,
                    page = page.number,
                    size = page.numberOfElements,
                    totalElements = page.totalElements,
                    totalPages = page.totalPages,
                    hasNextPage = page.hasNext()
            )
        }
    }
}