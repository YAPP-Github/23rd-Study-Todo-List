package site.yapp.study.todolist.api.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import site.yapp.study.todolist.api.todo.dto.request.TodoBulkCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.request.TodoUpdateRequestDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoGetResponseDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoToggleGetResponseDto;
import site.yapp.study.todolist.api.todo.service.TodoService;
import site.yapp.study.todolist.common.response.ApiResponse;
import site.yapp.study.todolist.common.response.SuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
@Tag(name = "[Todo] 투두리스트 관련 API (V1)")
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "할일 생성 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "할일 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "할일 생성 실패", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Object> createTodo(@RequestBody TodoCreateRequestDto todoCreateRequestDto) {
        todoService.createTodo(todoCreateRequestDto);
        return ApiResponse.success(SuccessCode.CREATE_TODO_SUCCESS);
    }

    @Operation(summary = "벌크 할일 생성 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "벌크 할일 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "벌크 할일 생성 실패", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content)
    })
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Object> createBulkTodo(@RequestBody TodoBulkCreateRequestDto todoBulkCreateRequestDto) {
        todoService.createBulkTodo(todoBulkCreateRequestDto);
        return ApiResponse.success(SuccessCode.CREATE_BULK_TODO_SUCCESS);
    }

    @Operation(summary = "할일 전체 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "할일 전체 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "할일 전체 조회 실패", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content)
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<TodoGetResponseDto>> getAllTodo() {
        List<TodoGetResponseDto> response = todoService.getAllTodo();
        return ApiResponse.success(SuccessCode.GET_TODO_SUCCESS, response);
    }

    @Operation(summary = "할일 개별 조회 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "할일 개별 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "할일 개별 조회 실패", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 할일", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content)
    })
    @GetMapping("/{todoId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<TodoGetResponseDto> getEachTodo(@PathVariable Long todoId) {
        TodoGetResponseDto response = todoService.getEachTodo(todoId);
        return ApiResponse.success(SuccessCode.GET_EACH_TODO_SUCCESS, response);
    }

    @Operation(summary = "할일 수정 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "할일 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "할일 수정 실패", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 할일", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content)
    })
    @PatchMapping("/{todoId}")
    public ApiResponse<Object> updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto) {
        todoService.updateTodo(todoId, todoUpdateRequestDto);
        return ApiResponse.success(SuccessCode.UPDATE_TODO_SUCCESS);
    }

    @Operation(summary = "할일 삭제 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "할일 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "할일 삭제 실패", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 할일", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content)
    })
    @DeleteMapping("/{todoId}")
    public ApiResponse<Object> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return ApiResponse.success(SuccessCode.DELETE_TODO_SUCCESS);
    }

    @Operation(summary = "할일 완료 토글 변경 API")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "할일 완료 토글 변경 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "할일 완료 토글 변경 실패", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 할일", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 내부 오류", content = @Content)
    })
    @PatchMapping("/toggle/{todoId}")
    public ApiResponse<TodoToggleGetResponseDto> toggleTodoStatus(@PathVariable Long todoId, @RequestParam Boolean isCompleted) {
        TodoToggleGetResponseDto response = todoService.toggleTodoStatus(todoId, isCompleted);
        return ApiResponse.success(SuccessCode.TOGGLE_TODO_SUCCESS, response);
    }
}
