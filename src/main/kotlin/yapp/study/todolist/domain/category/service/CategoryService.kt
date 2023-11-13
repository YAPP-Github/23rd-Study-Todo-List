package yapp.study.todolist.domain.category.service

import org.springframework.stereotype.Service
import yapp.study.todolist.common.error.errors.NotFoundException
import yapp.study.todolist.domain.base.IdGenerator
import yapp.study.todolist.domain.category.dto.*
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
        private val idGenerator: IdGenerator
) {
    fun createCategory(categoryNameDto: CategoryNameDto): Long {
        val generatedId = idGenerator.getAndIncreaseCategoryId()
        val category = Category.toEntity(generatedId, categoryNameDto.name)
        categoryRepository.save(category)
        return generatedId
    }

    fun getCategories(): CategoriesDto {
        return CategoriesDto.toDto(categoryRepository.findAll().map {CategoryDto.toDto(it)})
    }

    fun deleteCategory(id: Long) {
        categoryRepository.findById(id)
                ?.let { categoryRepository.deleteById(id) }
                ?: throw NotFoundException("not exist category")
        val todoIds = todoRepository.findByCategoryId(id).map { it.id }
        commentRepository.deleteAllByTodoIdIn(todoIds)
        todoRepository.deleteByIdIn(todoIds)
    }

    fun updateCategory(id: Long, categoryNameDto: CategoryNameDto) {
        categoryRepository.findById(id)
                ?.let {
                    it.updateName(categoryNameDto.name)
                    categoryRepository.save(it)
                }
                ?: throw NotFoundException("not exist category")
    }

    fun getCategoriesWithTodo(): CategoriesWithTodosDto{
        val categorys = categoryRepository.findAll()
        val todos = todoRepository.findAll()
        return CategoriesWithTodosDto.toDto(categorys.map {
            CategoryWithTodosDto.toDto(it, todos.filter { todo: Todo ->  it.id == todo.categoryId })
        })
    }
}
