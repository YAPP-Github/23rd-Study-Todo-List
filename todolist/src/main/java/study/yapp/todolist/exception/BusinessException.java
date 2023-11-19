package study.yapp.todolist.exception;

import lombok.Getter;
import study.yapp.todolist.common.ResponseCode;

@Getter
public class BusinessException extends RuntimeException {
    private final ResponseCode responseCode;

    public BusinessException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public BusinessException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

}