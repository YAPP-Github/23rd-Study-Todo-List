package yapp.study.todolist.domain.base

import org.springframework.stereotype.Component

@Component
class IdGenerator(
        private var categoryId: Long = 0,
        private var taskId: Long = 0,
        private var commentId: Long = 0,
) {
    @Synchronized
    fun getAndIncreaseCategoryId(): Long {
        return ++categoryId
    }

    @Synchronized
    fun getAndIncreaseTaskId(): Long {
        return ++taskId
    }

    @Synchronized
    fun getAndIncreaseCommentId(): Long {
        return ++commentId
    }
}