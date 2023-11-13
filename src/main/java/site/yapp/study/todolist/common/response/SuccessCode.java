package site.yapp.study.todolist.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    /* 201 CREATED */
    CREATE_TODO_SUCCESS(CREATED, "할일 생성 성공");

    private final HttpStatus status;
    private final String message;
}
