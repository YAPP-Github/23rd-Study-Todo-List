package yapp.study.todolist.common.error.errors

import org.springframework.http.HttpStatus
import yapp.study.todolist.common.error.BaseException

class InternalServerException(
        errorMessage: String
) : BaseException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage)