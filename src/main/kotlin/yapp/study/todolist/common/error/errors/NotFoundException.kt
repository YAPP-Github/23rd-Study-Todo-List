package yapp.study.todolist.common.error.errors

import org.springframework.http.HttpStatus
import yapp.study.todolist.common.error.BaseException

class NotFoundException(
        errorMessage: String
) : BaseException(HttpStatus.NOT_FOUND, errorMessage)