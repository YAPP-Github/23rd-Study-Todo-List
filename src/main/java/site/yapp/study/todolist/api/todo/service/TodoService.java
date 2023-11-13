package site.yapp.study.todolist.api.todo.service;

import org.springframework.transaction.annotation.Transactional;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;

@Transactional
public interface TodoService {
    void createTodo(TodoCreateRequestDto todoCreateRequestDto);
}
