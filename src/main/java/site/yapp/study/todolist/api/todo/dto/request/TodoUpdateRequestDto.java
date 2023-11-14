package site.yapp.study.todolist.api.todo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoUpdateRequestDto {

    private String category;

    private String content;
}
