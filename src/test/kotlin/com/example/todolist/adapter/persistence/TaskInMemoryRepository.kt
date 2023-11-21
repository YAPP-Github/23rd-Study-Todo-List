package com.example.todolist.adapter.persistence

import com.example.todolist.application.model.Page
import com.example.todolist.application.model.PageInfo
import com.example.todolist.application.model.Pageable
import com.example.todolist.application.port.TaskRepository
import com.example.todolist.domain.Task
import org.springframework.stereotype.Repository
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class TaskInMemoryRepository(
    private val tasks: ConcurrentMap<UUID, Task> = ConcurrentHashMap()
): TaskRepository {
    override fun findAllOrderByCreatedAtAsc(pageable: Pageable): Page<Task> {
        val pageData = tasks.values.sortedBy { it.createdAt }
            .drop((pageable.page - 1) * pageable.size)
            .take(pageable.size)
        return Page(
            PageInfo(tasks.size.toLong(), pageable),
            pageData
        )
    }

    override fun findByUuidOrNull(uuid: UUID): Task? {
        return tasks[uuid]
    }

    override fun save(task: Task): Task {
        tasks[task.uuid] = task
        return task
    }

    override fun delete(task: Task) {
        tasks.remove(task.uuid)
    }
}