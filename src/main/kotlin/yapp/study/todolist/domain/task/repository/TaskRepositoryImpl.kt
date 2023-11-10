package yapp.study.todolist.domain.task.repository

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.task.entity.Task

@Component
class TaskRepositoryImpl(
        private val tasks: MutableMap<Long, Task> = mutableMapOf()
) : TaskRepository {
    override fun save(task: Task) {
        tasks[task.id] = task
    }

    override fun getTasks(): List<Task> {
        return tasks.values.toList()
    }

    override fun findById(id: Long): Task? {
        return tasks[id]
    }

    override fun deleteById(id: Long) {
        tasks.remove(id)
    }
}