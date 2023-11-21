package yapp.study.todolist.domain.category.dto

import yapp.study.todolist.domain.category.entity.Category

class CategoryDto(
        val id: Long,
        val name: String
){
    companion object {
        fun toDto(category: Category): CategoryDto {
            return CategoryDto(
                    id = category.id,
                    name = category.name
            )
        }
    }
}