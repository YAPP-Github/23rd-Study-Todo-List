package yapp.study.todolist.domain.category.dto

import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.todo.entity.Todo

class CategoryWithTodosDto(
        val id: Long,
        val name: String,
        val todos: List<Todo>
){
    companion object {
        fun toDto(category: Category, todos: List<Todo>): CategoryWithTodosDto {
            return CategoryWithTodosDto(
                    id = category.id!!,
                    name = category.name,
                    todos = todos
            )
        }
    }
}