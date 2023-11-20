package site.yapp.study.todolist.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    /* 201 CREATED */
    CREATE_TODO_SUCCESS(CREATED, "할일 생성 성공"),
    CREATE_BULK_TODO_SUCCESS(CREATED, "벌크 할일 생성 성공"),

    /* 200 OK */
    GET_TODO_SUCCESS(OK, "할일 전체 조회 성공"),
    GET_EACH_TODO_SUCCESS(OK, "할일 개별 조회 성공"),
    UPDATE_TODO_SUCCESS(OK, "할일 수정 성공"),
    DELETE_TODO_SUCCESS(OK, "할일 삭제 성공"),
    TOGGLE_TODO_SUCCESS(OK, "할일 완료 토글 변경 성공");

    private final HttpStatus status;
    private final String message;
}
