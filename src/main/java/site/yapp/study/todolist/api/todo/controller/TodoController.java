package site.yapp.study.todolist.api.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoGetResponseDto;
import site.yapp.study.todolist.api.todo.service.TodoService;
import site.yapp.study.todolist.common.response.ApiResponse;
import site.yapp.study.todolist.common.response.SuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Object> createTodo(@RequestBody TodoCreateRequestDto todoCreateRequestDto) {
        todoService.createTodo(todoCreateRequestDto);
        return ApiResponse.success(SuccessCode.CREATE_TODO_SUCCESS);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<TodoGetResponseDto>> getAllTodo() {
        List<TodoGetResponseDto> response = todoService.getAllTodo();
        return ApiResponse.success(SuccessCode.GET_TODO_SUCCESS, response);
    }

    @GetMapping("/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<TodoGetResponseDto> getEachTodo(@PathVariable Long todoId) {
        TodoGetResponseDto response = todoService.getEachTodo(todoId);
        return ApiResponse.success(SuccessCode.GET_EACH_TODO_SUCCESS, response);
    }
}
