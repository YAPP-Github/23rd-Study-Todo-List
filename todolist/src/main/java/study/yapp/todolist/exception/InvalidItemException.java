package study.yapp.todolist.exception;

import study.yapp.todolist.common.ResponseCode;

public class InvalidItemException extends BusinessException{
    public InvalidItemException(ResponseCode responseCode) {
        super(responseCode);
    }

    public InvalidItemException(String message, ResponseCode responseCode) {
        super(message, responseCode);
    }
}