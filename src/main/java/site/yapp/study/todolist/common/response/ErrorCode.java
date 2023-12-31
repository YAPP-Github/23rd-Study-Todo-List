package site.yapp.study.todolist.common.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode {
    /* 400 BAD REQUEST */
    FAILED_BULK_TODO(BAD_REQUEST, "벌크 할일 생성 실패"),

    /* 404 NOT FOUND */
    NOT_FOUND_TODO(NOT_FOUND, "존재하지 않는 할일입니다."),
    DELETED_TODO(NOT_FOUND, "삭제된 할일입니다.");

    private final HttpStatus status;
    private final String message;

}
