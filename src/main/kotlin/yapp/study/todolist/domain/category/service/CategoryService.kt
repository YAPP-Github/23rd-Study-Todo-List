package yapp.study.todolist.domain.category.service

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yapp.study.todolist.common.error.errors.NotFoundException
import yapp.study.todolist.common.response.PageResponse
import yapp.study.todolist.domain.category.dto.CategoryDto
import yapp.study.todolist.domain.category.dto.CategoryNameDto
import yapp.study.todolist.domain.category.dto.CategoryWithTodosDto
import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.todo.entity.Todo
import yapp.study.todolist.domain.todo.repository.TodoRepository

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val todoRepository: TodoRepository,
        private val commentRepository: CommentRepository,
) {
    @Transactional
    fun createCategory(categoryNameDto: CategoryNameDto): Long {
        val category = categoryRepository.save(Category.toEntity(categoryNameDto.name))
        return category.id!!
    }

    @Transactional(readOnly = true)
    fun getCategories(pageable: Pageable): PageResponse<CategoryDto> {
        return PageResponse.toResponse(categoryRepository.findAll(pageable).map {CategoryDto.toDto(it)})
    }

    @Transactional
    fun deleteCategory(id: Long) {
        categoryRepository.findByIdOrNull(id)
                ?.let { categoryRepository.deleteById(id) }
                ?: throw NotFoundException("not exist category")
        val todoIds = todoRepository.findByCategoryId(id).map { it.id!! }
        commentRepository.deleteAllByTodoIdIn(todoIds)
        todoRepository.deleteAllByIdIn(todoIds)
    }

    @Transactional
    fun updateCategory(id: Long, categoryNameDto: CategoryNameDto) {
        categoryRepository.findByIdOrNull(id)
                ?.let {
                    it.updateName(categoryNameDto.name)
                }
                ?: throw NotFoundException("not exist category")
    }

    @Transactional(readOnly = true)
    fun getCategoriesWithTodo(pageable: Pageable): PageResponse<CategoryWithTodosDto> {
        val categorys = categoryRepository.findAll(pageable)
        val todos = todoRepository.findAllByCategoryIdIn(categorys.content.map { it.id!! })
        return PageResponse.toResponse(categorys.map {
            CategoryWithTodosDto.toDto(it, todos.filter { todo: Todo ->  it.id == todo.categoryId })
        })
    }
}
