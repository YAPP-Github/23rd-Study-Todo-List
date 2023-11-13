package yapp.study.todolist.domain.category.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import yapp.study.todolist.domain.category.dto.CategoriesDto
import yapp.study.todolist.domain.category.dto.CategoriesWithTodosDto
import yapp.study.todolist.domain.category.dto.CategoryNameDto
import yapp.study.todolist.domain.category.service.CategoryService

@RestController
@RequestMapping(value = ["/categories"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
class CategoryController(
        private val categoryService: CategoryService
) {
    @PostMapping
    fun createCategory(@RequestBody categoryNameDto: CategoryNameDto) {
        categoryService.createCategory(categoryNameDto)
    }

    @GetMapping
    fun getCategories(): CategoriesDto {
        return categoryService.getCategories()
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable("id") id: Long) {
        categoryService.deleteCategory(id)
    }

    @PatchMapping("/{id}")
    fun updateCategory(@PathVariable("id") id: Long,
                       @RequestBody categoryNameDto: CategoryNameDto) {
        categoryService.updateCategory(id, categoryNameDto)
    }

    @GetMapping("/todos")
    fun getCategoriesWithTodo(): CategoriesWithTodosDto {
        return categoryService.getCategoriesWithTodo()
    }
}