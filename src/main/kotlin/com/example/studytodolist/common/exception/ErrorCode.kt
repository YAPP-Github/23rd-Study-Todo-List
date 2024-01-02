package com.example.studytodolist.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val code: Int, val message: String) {
    /**
     * 200X
     * Todo 관련 에러
     */
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, 2000, "해당하는 Todo가 존재하지 않습니다."),
    TODO_BULK_INSERT_FAILED(HttpStatus.BAD_REQUEST, 2001, "TODO Bulk 삽입에 실패했습니다.")
}