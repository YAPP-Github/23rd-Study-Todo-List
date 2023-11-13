package site.yapp.study.todolist.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode {
    /* 404 NOT FOUND */
    NOT_FOUND_TODO(NOT_FOUND, "존재하지 않는 할일입니다.");

    private final HttpStatus status;
    private final String message;

}
