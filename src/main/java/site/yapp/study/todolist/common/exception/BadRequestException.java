package site.yapp.study.todolist.common.exception;

import org.springframework.http.HttpStatus;
import site.yapp.study.todolist.common.response.ErrorCode;

public class BadRequestException extends BaseException {

    public BadRequestException(ErrorCode errorCode) {
        super(HttpStatus.BAD_REQUEST, errorCode.getMessage());
    }
}
