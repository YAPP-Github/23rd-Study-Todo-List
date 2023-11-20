package yapp.study.todolist.common.error

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception

@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {
    override fun handleExceptionInternal(ex: Exception, body: Any?, headers: HttpHeaders, statusCode: HttpStatusCode, request: WebRequest): ResponseEntity<Any>? {
        val status = statusCode.value()
        val errorResponse = ErrorResponse(status = status, reason = ex.message)
        return super.handleExceptionInternal(ex, errorResponse, headers, statusCode, request)
    }

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(
            e: BaseException,
            request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        logger.info(e.message)
        val errorResponse = ErrorResponse(status = e.httpStatus.value(), reason = e.errorMessage)
        return ResponseEntity.status(e.httpStatus.value()).body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(
            e: Exception,
            request: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        logger.info(e.message)
        val errorResponse = ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                reason = "관리자에게 문의 부탁드립니다."
        )
        return ResponseEntity.internalServerError().body(errorResponse)
    }

}