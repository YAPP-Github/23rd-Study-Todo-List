package com.example.todolist.adapter.persistence

import com.example.todolist.application.model.Page
import com.example.todolist.application.model.PageInfo
import com.example.todolist.application.model.Pageable
import com.example.todolist.application.port.TaskRepository
import com.example.todolist.domain.Task
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.util.*

interface TaskJpaRepository: JpaRepository<TaskEntity, Long> {
    fun findByUuid(uuid: UUID): TaskEntity?

    @Query("select t from TaskEntity t order by t.createdAt asc")
    fun findAllOrderByCreatedAtAsc(pageable: org.springframework.data.domain.Pageable): org.springframework.data.domain.Page<TaskEntity>
}

@Transactional(readOnly = true)
@Repository
class TaskRepositoryImpl(
    private val taskJpaRepository: TaskJpaRepository,
    private val jdbcTemplate: JdbcTemplate
): TaskRepository {
    override fun findAllOrderByCreatedAtAsc(pageable: Pageable): Page<Task> {
        val taskPage = taskJpaRepository.findAllOrderByCreatedAtAsc(
            PageRequest.of(pageable.page - 1, pageable.size)
        )
        return Page(
            PageInfo(taskPage.totalElements, pageable),
            taskPage.content.map { it.toDomain() }
        )
    }

    override fun findByUuidOrNull(uuid: UUID): Task? {
        return taskJpaRepository.findByUuid(uuid)?.toDomain()
    }

    @Transactional
    override fun delete(task: Task) {
        val taskEntity = TaskEntity(task)
        taskJpaRepository.delete(taskEntity)
    }

    @Transactional
    override fun save(task: Task): Task {
        val taskEntity = TaskEntity(task)
        taskJpaRepository.save(taskEntity)
        return taskEntity.toDomain()
    }

    @Transactional
    override fun saveInBatch(tasks: List<Task>): Int {
        val result = jdbcTemplate.batchUpdate(
            "insert into task (uuid, title, description, is_complete, created_at) values (?, ?, ?, ?, ?)",
            tasks,
            tasks.size
        ) { ps, task ->
            ps.setString(1, task.uuid.toString())
            ps.setString(2, task.title)
            ps.setString(3, task.description)
            ps.setBoolean(4, task.isComplete)
            ps.setTimestamp(5, Timestamp.valueOf(task.createdAt.toLocalDateTime()))
        }
        return result.sumOf { it.size }
    }
}