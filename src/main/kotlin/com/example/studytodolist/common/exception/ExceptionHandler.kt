package com.example.studytodolist.common.exception

import com.example.studytodolist.common.dto.BaseExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException::class)
    protected fun handleBaseException(e: BusinessException): ResponseEntity<BaseExceptionResponse> {
        return ResponseEntity.status(e.errorCode.status).body(BaseExceptionResponse(e.errorCode))
    }
}