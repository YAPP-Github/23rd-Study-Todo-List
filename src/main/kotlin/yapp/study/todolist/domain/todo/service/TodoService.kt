package yapp.study.todolist.domain.todo.service

import org.springframework.stereotype.Service
import yapp.study.todolist.domain.base.IdGenerator
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
            throw RuntimeException("not exist category")
        }
        val generatedId = idGenerator.getAndIncreaseTodoId()
        todoRepository.save(Todo.toEntity(generatedId, todoDetailDto))
        return generatedId
    }

    fun getTodos(): TodosDto {
        return TodosDto.toDto(todoRepository.findAll().map {TodoDto.toDto(it)})
    }

    fun updateTodo(id: Long, todoDetailDto: TodoDetailDto) {
        if (!categoryRepository.existById(todoDetailDto.categoryId)) {
            throw RuntimeException("not exist category")
        }
        todoRepository.findById(id)
                ?.let {
                    it.update(todoDetailDto)
                    todoRepository.save(it)
                }
                ?: throw RuntimeException("not exist todo")
    }

    fun deleteTodo(id: Long) {
        when (todoRepository.existById(id)) {
            true -> todoRepository.deleteById(id)
            false -> throw RuntimeException("not exist todo")
        }
        commentRepository.deleteAllByTodoId(id)
    }

    fun updateDoneTodo(id: Long, done: Boolean) {
        todoRepository.findById(id)
                ?.let {
                    when (done) {
                        true -> it.doneTodo()
                        false -> it.ongoingTodo()
                    }
                    todoRepository.save(it)
                }
                ?: throw RuntimeException("not exist todo")
    }

    fun getTodoWithComments(id: Long): TodoCommentDto {
        val todo = todoRepository.findById(id) ?: throw RuntimeException("not exist todo")
        val comments = commentRepository.findByTodoId(id).map { CommentDto.toDto(it) }
        return TodoCommentDto.toDto(todo, comments)
    }
}