package site.yapp.study.todolist.api.todo.service;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import site.yapp.study.todolist.api.todo.dto.request.TodoBulkCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.request.TodoUpdateRequestDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoGetResponseDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoToggleGetResponseDto;

public interface TodoService {
    void createTodo(TodoCreateRequestDto requestDto);
    void createBulkTodo(TodoBulkCreateRequestDto requestDto);
    CollectionModel<EntityModel<TodoGetResponseDto>> getAllTodo();
    EntityModel<TodoGetResponseDto> getEachTodo(Long todoId);
    void updateTodo(Long todoId, TodoUpdateRequestDto requestDto);
    void deleteTodo(Long todoId);
    TodoToggleGetResponseDto toggleTodoStatus(Long todoId, Boolean isCompleted);
}
