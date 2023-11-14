package site.yapp.study.todolist.api.todo.service;

import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoGetAllResponseDto;

import java.util.List;

public interface TodoService {
    void createTodo(TodoCreateRequestDto todoCreateRequestDto);
    List<TodoGetAllResponseDto> getAllTodo();
}
