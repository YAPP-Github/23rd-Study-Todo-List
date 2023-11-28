package yapp.study.todolist.common.response

import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.util.PatternMatchUtils
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import yapp.study.todolist.common.const.SWAGGER_PATTERNS

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
        val servletResponse = (response as ServletServerHttpResponse).servletResponse
        val servletRequest = (request as ServletServerHttpRequest).servletRequest
        val httpStatus = HttpStatus.resolve(servletResponse.status)!!
        val path = servletRequest.servletPath

        if (PatternMatchUtils.simpleMatch(SWAGGER_PATTERNS, path)){
            return body
        }

        val data = if (body is Map<*, *>) {
            body.values.first()
        } else {
            body
        }

        val statusCode = if (body is Map<*, *> && httpStatus.is2xxSuccessful) {
            body.keys.first() as Int
        } else {
            statusProvider(servletRequest.method)
        }

        return if (httpStatus.is2xxSuccessful) {
            ResponseEntity.status(statusCode).body(SuccessResponse(data))
        } else {
            body
        }
    }

    private fun statusProvider(method: String): Int {
        if (method == "POST") return 201
        return if (method == "DELETE") 204 else 200
    }
}