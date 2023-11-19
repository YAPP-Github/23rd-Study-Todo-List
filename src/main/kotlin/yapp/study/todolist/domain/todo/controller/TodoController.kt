package yapp.study.todolist.domain.todo.controller

import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import yapp.study.todolist.common.response.PageResponse
import yapp.study.todolist.domain.todo.dto.TodoCommentDto
import yapp.study.todolist.domain.todo.dto.TodoDetailDto
import yapp.study.todolist.domain.todo.dto.TodoDto
import yapp.study.todolist.domain.todo.service.TodoService

@RestController
@RequestMapping(value = ["/v1/todos"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
class TodoController(
        private val todoService: TodoService
) {
    @PostMapping
    fun createTodo(@RequestBody todoDetailDto: TodoDetailDto) {
        todoService.createTodo(todoDetailDto)
    }

    @GetMapping
    fun getTodos(@PageableDefault(size = 10) pageable: Pageable): PageResponse<TodoDto> {
        return todoService.getTodos(pageable)
    }

    @PatchMapping("/{id}")
    fun updateTodo(@PathVariable("id") id: Long,
                   @RequestBody todoDetailDto: TodoDetailDto) {
        todoService.updateTodo(id, todoDetailDto)
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable("id") id: Long) {
        todoService.deleteTodo(id);
    }

    @PatchMapping("/{id}/done")
    fun updateTodoDone(@PathVariable("id") id: Long,
                       @RequestParam("done") done: Boolean) {
        todoService.updateDoneTodo(id, done)
    }

    @GetMapping("/{id}/comments")
    fun getTodoWithComments(@PathVariable("id") id: Long): TodoCommentDto {
        return todoService.getTodoWithComments(id)
    }
}