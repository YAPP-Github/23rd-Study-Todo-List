package yapp.study.todolist.domain.todo.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import yapp.study.todolist.domain.todo.dto.TodoCommentDto
import yapp.study.todolist.domain.todo.dto.TodoDetailDto
import yapp.study.todolist.domain.todo.dto.TodosDto
import yapp.study.todolist.domain.todo.service.TodoService

@RestController
@RequestMapping("/todos")
class TodoController(
        private val todoService: TodoService
) {
    @PostMapping
    fun createTodo(@RequestBody todoDetailDto: TodoDetailDto) {
        todoService.createTodo(todoDetailDto)
    }

    @GetMapping
    fun getTodos(): TodosDto {
        return todoService.getTodos()
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