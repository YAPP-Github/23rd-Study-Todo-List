package site.yapp.study.todolist.api.todo.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class TodoBulkCreateRequestDto {

    private List<TodoCreateRequestDto> todoList;
}
