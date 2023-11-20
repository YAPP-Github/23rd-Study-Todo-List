package site.yapp.study.todolist.api.todo.service;

import site.yapp.study.todolist.api.todo.dto.request.TodoBulkCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.request.TodoUpdateRequestDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoGetResponseDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoToggleGetResponseDto;

import java.util.List;

public interface TodoService {
    void createTodo(TodoCreateRequestDto requestDto);
    void createBulkTodo(TodoBulkCreateRequestDto requestDto);
    List<TodoGetResponseDto> getAllTodo();
    TodoGetResponseDto getEachTodo(Long todoId);
    void updateTodo(Long todoId, TodoUpdateRequestDto requestDto);
    void deleteTodo(Long todoId);
    TodoToggleGetResponseDto toggleTodoStatus(Long todoId, Boolean isCompleted);
}
