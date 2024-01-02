package com.example.studytodolist.common.exception

import org.springframework.http.HttpStatus

class BusinessException(override val message: String, val code: Int, val status: HttpStatus): RuntimeException() {
    constructor(errorCode: ErrorCode) : this(errorCode.message, errorCode.code, errorCode.status)
}