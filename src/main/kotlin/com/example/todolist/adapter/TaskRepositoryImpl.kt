package com.example.todolist.adapter

import com.example.todolist.application.port.TaskRepository
import com.example.todolist.domain.Task
import org.springframework.stereotype.Repository
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Repository
class TaskRepositoryImpl(
    private val tasks: ConcurrentMap<UUID, Task> = ConcurrentHashMap()
): TaskRepository {
    override fun findAll(): List<Task> {
        return tasks.values.toList()
    }

    override fun findByUuid(uuid: UUID): Task? {
        return tasks[uuid]
    }

    override fun add(task: Task): Task {
        tasks[task.uuid] = task
        return task
    }

    override fun remove(task: Task) {
        tasks.remove(task.uuid)
    }
}