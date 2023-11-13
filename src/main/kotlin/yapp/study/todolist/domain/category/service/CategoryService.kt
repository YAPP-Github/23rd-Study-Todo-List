package yapp.study.todolist.domain.category.service

import org.springframework.stereotype.Service
import yapp.study.todolist.domain.base.IdGenerator
import yapp.study.todolist.domain.category.dto.*
import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.task.entity.Task
import yapp.study.todolist.domain.task.repository.TaskRepository

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val taskRepository: TaskRepository,
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
                ?: throw RuntimeException("not exist category")
        val taskIds = taskRepository.findByCategoryId(id).map { it.id }
        commentRepository.deleteAllByTaskIdIn(taskIds)
        taskRepository.deleteByIdIn(taskIds)
    }

    fun updateCategory(id: Long, categoryNameDto: CategoryNameDto) {
        categoryRepository.findById(id)
                ?.let {
                    it.updateName(categoryNameDto.name)
                    categoryRepository.save(it)
                }
                ?: throw RuntimeException("not exist category")
    }

    fun getCategoriesWithTask(): CategoriesWithTasksDto{
        val categorys = categoryRepository.findAll()
        val tasks = taskRepository.findAll()
        return CategoriesWithTasksDto.toDto(categorys.map {
            CategoryWithTasksDto.toDto(it, tasks.filter { task: Task ->  it.id == task.categoryId })
        })
    }
}
