package yapp.study.todolist.common.response

import jakarta.servlet.ServletRequest
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice

@RestControllerAdvice
class SuccessResponseAdvice: ResponseBodyAdvice<Any?> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
        return true
    }

    override fun beforeBodyWrite(body: Any?,
                                 returnType: MethodParameter,
                                 selectedContentType: MediaType,
                                 selectedConverterType: Class<out HttpMessageConverter<*>>,
                                 request: ServerHttpRequest,
                                 response: ServerHttpResponse
    ): Any? {
        val servletRequest = (request as ServletServerHttpRequest).servletRequest
        val servletResponse = (response as ServletServerHttpResponse).servletResponse
        val httpStatus = HttpStatus.resolve(servletResponse.status)!!
        return if (httpStatus.is2xxSuccessful) {
            SuccessResponse(statusProvider(servletRequest.method), body)
        } else {
            body
        }
    }

    private fun statusProvider(method: String): Int {
        return when (method) {
            "POST" -> 201
            "DELETE" -> 204
            else -> 200
        }
    }
}