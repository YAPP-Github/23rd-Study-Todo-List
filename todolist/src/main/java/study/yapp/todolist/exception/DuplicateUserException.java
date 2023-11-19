package study.yapp.todolist.exception;

import study.yapp.todolist.common.ResponseCode;

public class DuplicateUserException extends BusinessException{
    public DuplicateUserException(ResponseCode responseCode) {
        super(responseCode);
    }

    public DuplicateUserException(String message, ResponseCode responseCode) {
        super(message, responseCode);
    }
}