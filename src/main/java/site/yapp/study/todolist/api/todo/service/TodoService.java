package site.yapp.study.todolist.api.todo.service;

import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;

public interface TodoService {
    void createTodo(TodoCreateRequestDto todoCreateRequestDto);
}
