package com.example.studytodolist.common.handler

import com.example.studytodolist.common.dto.BaseExceptionResponse
import com.example.studytodolist.common.exception.BusinessException
import com.example.studytodolist.logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    val log = logger()
    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException::class)
    protected fun handleBaseException(e: BusinessException): ResponseEntity<BaseExceptionResponse> {
        log.error(e.message)
        return ResponseEntity.status(e.status).body(BaseExceptionResponse(e.code, e.message))
    }
}