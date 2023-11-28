package site.yapp.study.todolist.api.todo.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
public class TodoCreateRequestDto {

    private String category;

    private String content;

    @Builder
    public TodoCreateRequestDto(String category, String content) {
        this.category = category;
        this.content = content;
    }
}
