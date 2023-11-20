package yapp.study.todolist.domain.base

import org.springframework.stereotype.Component

@Component
class IdGenerator(
        private var categoryId: Long = 0,
        private var todoId: Long = 0,
        private var commentId: Long = 0,
) {
    @Synchronized
    fun getAndIncreaseCategoryId(): Long {
        return ++categoryId
    }

    @Synchronized
    fun getAndIncreaseTodoId(): Long {
        return ++todoId
    }

    @Synchronized
    fun getAndIncreaseCommentId(): Long {
        return ++commentId
    }
}