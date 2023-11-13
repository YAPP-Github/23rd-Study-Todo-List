package yapp.study.todolist.common.error

import org.springframework.http.HttpStatus

open class BaseException (
        val httpStatus: HttpStatus,
        val errorMessage: String
) : RuntimeException()