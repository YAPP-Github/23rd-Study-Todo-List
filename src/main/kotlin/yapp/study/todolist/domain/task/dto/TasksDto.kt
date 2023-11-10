package yapp.study.todolist.domain.task.dto

class TasksDto(
        val tasks: List<TaskDto>
) {
    companion object {
        fun toDto(tasks: List<TaskDto>): TasksDto {
            return TasksDto(
                    tasks = tasks
            )
        }
    }
}