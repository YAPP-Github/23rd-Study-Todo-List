package yapp.study.todolist.domain.task.repository

import yapp.study.todolist.domain.task.entity.Task

interface TaskRepository {
    fun save(task: Task)
    fun getTasks(): List<Task>
    fun findById(id: Long): Task?
    fun deleteById(id: Long)
}