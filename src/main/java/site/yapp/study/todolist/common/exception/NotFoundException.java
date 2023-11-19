package site.yapp.study.todolist.common.exception;

import org.springframework.http.HttpStatus;
import site.yapp.study.todolist.common.response.ErrorCode;

public class NotFoundException extends BaseException {

    public NotFoundException(ErrorCode errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode.getMessage());
    }

}
