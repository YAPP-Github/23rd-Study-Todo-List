package yapp.study.todolist.common.error.errors

import org.springframework.http.HttpStatus
import yapp.study.todolist.common.error.BaseException

class BadRequestException(
        errorMessage: String
) : BaseException(HttpStatus.BAD_REQUEST, errorMessage)