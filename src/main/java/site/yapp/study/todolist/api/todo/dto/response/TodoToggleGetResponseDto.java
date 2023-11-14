package site.yapp.study.todolist.api.todo.dto.response;

import lombok.Builder;
import lombok.Getter;
import site.yapp.study.todolist.api.todo.domain.Todo;

@Getter
@Builder
public class TodoToggleGetResponseDto {
    private Long todoId;

    private boolean isCompleted;

    public static TodoToggleGetResponseDto of(Todo todo) {
        return TodoToggleGetResponseDto.builder()
                .todoId(todo.getId())
                .isCompleted(todo.is_completed())
                .build();
    }
}
