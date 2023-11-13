package site.yapp.study.todolist.api.todo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class TodoCreateRequestDto {
    @NotNull(message = "카테고리를 입력해주세요.")
    private String category;

    @NotNull(message = "내용을 입력해주세요.")
    private String content;
}
