package yapp.study.todolist.domain.todo.service

import org.springframework.stereotype.Service
import yapp.study.todolist.common.error.errors.NotFoundException
import yapp.study.todolist.common.response.PageResponse
import yapp.study.todolist.domain.base.IdGenerator
import yapp.study.todolist.domain.base.PageParam
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.dto.CommentDto
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.todo.dto.TodoCommentDto
import yapp.study.todolist.domain.todo.dto.TodoDetailDto
import yapp.study.todolist.domain.todo.dto.TodoDto
import yapp.study.todolist.domain.todo.dto.TodosDto
import yapp.study.todolist.domain.todo.entity.Todo
import yapp.study.todolist.domain.todo.repository.TodoRepository

@Service
class TodoService(
        private val categoryRepository: CategoryRepository,
        private val todoRepository: TodoRepository,
        private val commentRepository: CommentRepository,
        private val idGenerator: IdGenerator
) {
    fun createTodo(todoDetailDto: TodoDetailDto): Long {
        if (!categoryRepository.existById(todoDetailDto.categoryId)) {
            throw NotFoundException("not exist category")
        }
        val generatedId = idGenerator.getAndIncreaseTodoId()
        todoRepository.save(Todo.toEntity(generatedId, todoDetailDto))
        return generatedId
    }

    fun getTodos(pageParam: PageParam): PageResponse<TodoDto> {
        return PageResponse.toResponse(pageParam, todoRepository.findAll().map {TodoDto.toDto(it)})
    }

    fun updateTodo(id: Long, todoDetailDto: TodoDetailDto) {
        if (!categoryRepository.existById(todoDetailDto.categoryId)) {
            throw NotFoundException("not exist category")
        }
        todoRepository.findById(id)
                ?.let {
                    it.update(todoDetailDto)
                    todoRepository.save(it)
                }
                ?: throw NotFoundException("not exist todo")
    }

    fun deleteTodo(id: Long) {
        when (todoRepository.existById(id)) {
            true -> todoRepository.deleteById(id)
            false -> throw NotFoundException("not exist todo")
        }
        commentRepository.deleteAllByTodoId(id)
    }

    fun updateDoneTodo(id: Long, done: Boolean) {
        todoRepository.findByIdAndIsDone(id, !done)
                ?.let {
                    when (done) {
                        true -> it.doneTodo()
                        false -> it.ongoingTodo()
                    }
                    todoRepository.save(it)
                }
                ?: throw NotFoundException("not exist todo")
    }

    fun getTodoWithComments(id: Long): TodoCommentDto {
        val todo = todoRepository.findById(id) ?: throw NotFoundException("not exist todo")
        val comments = commentRepository.findByTodoId(id).map { CommentDto.toDto(it) }
        return TodoCommentDto.toDto(todo, comments)
    }
}