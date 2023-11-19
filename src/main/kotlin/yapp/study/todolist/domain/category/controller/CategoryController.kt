package yapp.study.todolist.domain.category.controller

import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import yapp.study.todolist.common.response.PageResponse
import yapp.study.todolist.domain.category.dto.CategoryDto
import yapp.study.todolist.domain.category.dto.CategoryNameDto
import yapp.study.todolist.domain.category.dto.CategoryWithTodosDto
import yapp.study.todolist.domain.category.service.CategoryService

@RestController
@RequestMapping(value = ["/v1/categories"],
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
    fun getCategories(@PageableDefault(size = 10) pageable: Pageable): PageResponse<CategoryDto> {
        return categoryService.getCategories(pageable)
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
    fun getCategoriesWithTodo(@PageableDefault(size = 10) pageable: Pageable): PageResponse<CategoryWithTodosDto> {
        return categoryService.getCategoriesWithTodo(pageable)
    }
}