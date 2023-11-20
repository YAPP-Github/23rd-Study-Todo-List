package com.example.studytodolist.common.exception

import com.example.studytodolist.common.dto.BaseExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException::class)
    protected fun handleBaseException(e: RuntimeException): ResponseEntity<BaseExceptionResponse> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseExceptionResponse(e.message ?: "Runtime Error occurred"))
    }
}