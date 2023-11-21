package yapp.study.todolist.common.error

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleExceptionInternal(
            ex: java.lang.Exception,
            body: Any?,
            headers: HttpHeaders,
            statusCode: HttpStatusCode,
            request: WebRequest
    ): ResponseEntity<Any>? {
        logger.error(ex.message)
        val errorBody = mapOf("error msg" to ex.message)
        return super.handleExceptionInternal(ex, errorBody, headers, statusCode, request)
    }

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(
            e: BaseException,
            request: HttpServletRequest
    ): ResponseEntity<Map<String, String>> {
        logger.error(e.message)
        val body = mapOf("error msg" to e.errorMessage)
        return ResponseEntity.status(e.httpStatus.value()).body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
            e: Exception,
            request: HttpServletRequest
    ): ResponseEntity<Map<String, String>> {
        logger.error(e.message)
        val body = mapOf("error msg" to "관리자에게 문의 부탁드립니다.")
        return ResponseEntity.internalServerError().body(body)
    }

}