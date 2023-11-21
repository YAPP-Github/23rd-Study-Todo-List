package com.example.studytodolist.common.dto

import com.example.studytodolist.common.exception.ErrorCode

data class BaseExceptionResponse(
    val code: Int,
    val message: String
){
    constructor(errorCode: ErrorCode): this(errorCode.code, errorCode.message)
}