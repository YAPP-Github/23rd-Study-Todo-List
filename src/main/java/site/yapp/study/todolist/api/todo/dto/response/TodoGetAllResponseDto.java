package site.yapp.study.todolist.api.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class TodoGetAllResponseDto {
    private Long todoId;

    private String category;

    private String content;

    private boolean isCompleted;
}
