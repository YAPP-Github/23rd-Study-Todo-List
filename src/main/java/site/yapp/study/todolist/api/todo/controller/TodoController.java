package site.yapp.study.todolist.api.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.service.TodoService;
import site.yapp.study.todolist.common.response.ApiResponse;
import site.yapp.study.todolist.common.response.SuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Object> createCard(@RequestBody TodoCreateRequestDto todoCreateRequestDto) {
        todoService.createTodo(todoCreateRequestDto);
        return ApiResponse.success(SuccessCode.CREATE_TODO_SUCCESS);
    }
}
