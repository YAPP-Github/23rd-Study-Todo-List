package com.example.studytodolist.common.exception

class BusinessException(val errorCode: ErrorCode): RuntimeException() {
}