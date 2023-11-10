package yapp.study.todolist.domain.category.dto

import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.task.entity.Task

class CategoryWithTasksDto(
        val id: Long,
        val name: String,
        val tasks: List<Task>
){
    companion object {
        fun toDto(category: Category, tasks: List<Task>): CategoryWithTasksDto {
            return CategoryWithTasksDto(
                    id = category.id,
                    name = category.name,
                    tasks = tasks
            )
        }
    }
}