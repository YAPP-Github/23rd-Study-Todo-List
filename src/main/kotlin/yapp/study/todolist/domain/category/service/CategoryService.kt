package yapp.study.todolist.domain.category.service

import org.springframework.stereotype.Service
import yapp.study.todolist.domain.category.dto.CategoriesDto
import yapp.study.todolist.domain.category.dto.CategoryDto
import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.category.repository.CategoryRepository

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository
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
}