package study.yapp.todolist.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.yapp.todolist.common.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(BusinessException ex) {
        log.debug("===========================================================");
        log.debug("여기로 오는가?!");
        log.debug("===========================================================");

        final ErrorResponse response = ErrorResponse.of(ex.getResponseCode());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}