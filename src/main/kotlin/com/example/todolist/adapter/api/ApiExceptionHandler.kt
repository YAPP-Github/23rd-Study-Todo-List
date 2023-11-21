package com.example.todolist.adapter.api

import com.example.todolist.domain.TaskNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.reflect.KClass

enum class ErrorCode(
    val status: HttpStatus,
    val message: String,
    val exceptions: Set<KClass<out Exception>>
) {
    // Task
    TASK_NOT_FOUND(
        HttpStatus.NOT_FOUND,
        "해당 할 일을 찾을 수 없습니다.",
        setOf(TaskNotFoundException::class)
    ),
}

@RestControllerAdvice
class ApiExceptionHandler {
    private final val exceptionToErrorCode: Map<KClass<out Exception>, ErrorCode>

    init {
        val tempMap = hashMapOf<KClass<out Exception>, ErrorCode>()
        for (errorCode in ErrorCode.values()) {
            for (exception in errorCode.exceptions) {
                tempMap[exception] = errorCode
            }
        }
        exceptionToErrorCode = tempMap.toMap()
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<Any> {
        if (exceptionToErrorCode.containsKey(e::class)) {
            val errorCode = exceptionToErrorCode[e::class]!!
            return ResponseEntity.status(errorCode.status).body(errorCode.message)
        }

        throw e
    }
}