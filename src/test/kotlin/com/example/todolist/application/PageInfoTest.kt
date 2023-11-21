package com.example.todolist.application

import com.example.todolist.application.model.PageInfo
import com.example.todolist.application.model.Pageable
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PageInfoTest {

    @Test
    fun totalPageMustBe0WhenTotalSizeIs0() {
        val pageInfo = PageInfo(
            totalSize = 0,
            Pageable(page = 1, size = 10)
        )
        assertThat(pageInfo.totalPages).isEqualTo(0)
    }

    @Test
    fun totalPageWhenTotalPageRemainderSizeIs0() {
        val pageInfo = PageInfo(
            totalSize = 10,
            Pageable(page = 1, size = 10)
        )
        assertThat(pageInfo.totalPages).isEqualTo(1)
    }
}