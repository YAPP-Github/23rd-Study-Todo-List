package yapp.study.todolist.domain.category.dto

import yapp.study.todolist.domain.category.entity.Category

class CategoriesWithTasksDto (
        val categoriesWithTasks: List<CategoryWithTasksDto>
){
    companion object {
        fun toDto(categoriesWithTasks: List<CategoryWithTasksDto>): CategoriesWithTasksDto {
            return CategoriesWithTasksDto(
                    categoriesWithTasks = categoriesWithTasks
            )
        }
    }
}