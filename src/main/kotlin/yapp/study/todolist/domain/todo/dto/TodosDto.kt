package yapp.study.todolist.domain.todo.dto

class TodosDto(
        val todos: List<TodoDto>
) {
    companion object {
        fun toDto(todos: List<TodoDto>): TodosDto {
            return TodosDto(
                    todos = todos
            )
        }
    }
}