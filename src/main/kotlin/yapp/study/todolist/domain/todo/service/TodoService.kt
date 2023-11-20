package yapp.study.todolist.domain.todo.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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
) {
    @Transactional
    fun createTodo(todoDetailDto: TodoDetailDto): Long {
        if (!categoryRepository.existsById(todoDetailDto.categoryId)) {
            throw NotFoundException("not exist category")
        }
        val todo = todoRepository.save(Todo.toEntity(todoDetailDto))
        return todo.id!!
    }

    @Transactional(readOnly = true)
    fun getTodos(pageParam: PageParam): PageResponse<TodoDto> {
        return PageResponse.toResponse(pageParam, todoRepository.findAll().map {TodoDto.toDto(it)})
    }

    @Transactional
    fun updateTodo(id: Long, todoDetailDto: TodoDetailDto) {
        if (!categoryRepository.existsById(todoDetailDto.categoryId)) {
            throw NotFoundException("not exist category")
        }
        todoRepository.findByIdOrNull(id)
                ?.let {
                    it.update(todoDetailDto)
                }
                ?: throw NotFoundException("not exist todo")
    }

    @Transactional
    fun deleteTodo(id: Long) {
        when (todoRepository.existsById(id)) {
            true -> todoRepository.deleteById(id)
            false -> throw NotFoundException("not exist todo")
        }
        commentRepository.deleteAllByTodoId(id)
    }

    @Transactional
    fun updateDoneTodo(id: Long, done: Boolean) {
        todoRepository.findByIdAndIsDone(id, !done)
                ?.let {
                    when (done) {
                        true -> it.doneTodo()
                        false -> it.ongoingTodo()
                    }
                }
                ?: throw NotFoundException("not exist todo")
    }

    fun getTodoWithComments(id: Long): TodoCommentDto {
        val todo = todoRepository.findByIdOrNull(id) ?: throw NotFoundException("not exist todo")
        val comments = commentRepository.findAllByTodoId(id).map { CommentDto.toDto(it) }
        return TodoCommentDto.toDto(todo, comments)
    }
}