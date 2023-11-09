package yapp.study.todolist.domain.category.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import yapp.study.todolist.domain.category.dto.CategoriesDto
import yapp.study.todolist.domain.category.dto.CategoryDto
import yapp.study.todolist.domain.category.service.CategoryService
import java.time.ZoneId

@RestController
@RequestMapping("/categories")
class CategoryController(
        private val categoryService: CategoryService
) {
    @PostMapping
    fun createCategory(@RequestBody categoryDto: CategoryDto) {
        categoryService.createCategory(categoryDto)
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
                       @RequestBody categoryDto: CategoryDto) {
        categoryService.updateCategory(id, categoryDto)
    }
}