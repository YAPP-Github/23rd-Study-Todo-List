package yapp.study.todolist.domain.category.dto

import yapp.study.todolist.domain.category.entity.Category

class CategoriesDto (
        val categories: List<CategoryDto>
){
    companion object {
        fun toDto(categories: List<CategoryDto>): CategoriesDto {
            return CategoriesDto(
                    categories = categories
            )
        }
    }
}