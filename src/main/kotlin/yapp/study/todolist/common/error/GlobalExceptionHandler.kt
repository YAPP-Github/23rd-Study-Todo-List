package yapp.study.todolist.common.error

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(
            e: BaseException,
            request: HttpServletRequest
    ): ResponseEntity<Map<String, String>> {
        val body = mapOf("error msg" to e.errorMessage)
        return ResponseEntity.status(e.httpStatus.value()).body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
            e: Exception,
            request: HttpServletRequest
    ): ResponseEntity<Map<String, String>> {
        val body = mapOf("error msg" to "관리자에게 문의 부탁드립니다.")
        return ResponseEntity.internalServerError().body(body)
    }

}