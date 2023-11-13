package site.yapp.study.todolist.api.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class TodoCreateRequestDto {

    private String category;

    private String content;
}
