package yapp.study.todolist.domain.category.dto

class CategoriesWithTodosDto (
        val categoriesWithTodos: List<CategoryWithTodosDto>
){
    companion object {
        fun toDto(categoriesWithTodos: List<CategoryWithTodosDto>): CategoriesWithTodosDto {
            return CategoriesWithTodosDto(
                    categoriesWithTodos = categoriesWithTodos
            )
        }
    }
}