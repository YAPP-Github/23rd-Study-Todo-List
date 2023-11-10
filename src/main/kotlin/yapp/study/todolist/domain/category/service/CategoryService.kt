package yapp.study.todolist.domain.category.service

import org.springframework.stereotype.Service
import yapp.study.todolist.domain.category.dto.CategoriesDto
import yapp.study.todolist.domain.category.dto.CategoriesWithTasksDto
import yapp.study.todolist.domain.category.dto.CategoryDto
import yapp.study.todolist.domain.category.dto.CategoryWithTasksDto
import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.task.entity.Task
import yapp.study.todolist.domain.task.repository.TaskRepository

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val taskRepository: TaskRepository
) {
    fun createCategory(categoryDto: CategoryDto) {
        categoryRepository.findById(categoryDto.id)
                ?.let { throw RuntimeException() }
                ?: categoryRepository.save(Category.toEntity(categoryDto))
    }

    fun getCategories(): CategoriesDto {
        return CategoriesDto.toDto(categoryRepository.findAll().map {CategoryDto.toDto(it)})
    }

    fun deleteCategory(id: Long) {
        categoryRepository.findById(id)
                ?.let { categoryRepository.deleteById(id) }
                ?: throw RuntimeException()
    }

    fun updateCategory(id: Long, categoryDto: CategoryDto) {
        categoryRepository.findById(id)
                ?.let { categoryRepository.save(Category.toEntity(categoryDto)) }
                ?: throw RuntimeException()
    }

    fun getCategoriesWithTask(): CategoriesWithTasksDto{
        val categorys = categoryRepository.findAll()
        val tasks = taskRepository.findAll()
        return CategoriesWithTasksDto.toDto(categorys.map {
            CategoryWithTasksDto.toDto(it, tasks.filter { task: Task ->  it.id == task.categoryId })
        })
    }
}
