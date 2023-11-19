package study.yapp.todolist.exception;

import study.yapp.todolist.common.ResponseCode;

public class InvalidUserException extends BusinessException{
    public InvalidUserException(ResponseCode responseCode) {
        super(responseCode);
    }

    public InvalidUserException(String message, ResponseCode responseCode) {
        super(message, responseCode);
    }
}