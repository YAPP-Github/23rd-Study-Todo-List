package com.example.studytodolist.todo.repository

import com.example.studytodolist.todo.domain.Todo
import jakarta.transaction.Transactional
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement




@Repository
class TodoBulkInsertRepository(
    private val jdbcTemplate: JdbcTemplate
) {
    @Transactional
    fun saveAll(todoList: List<Todo?>): Int {
        return jdbcTemplate.batchUpdate("insert into todo (title, content, progress) values (?, ?, ?)",
            object: BatchPreparedStatementSetter {
                override fun setValues(ps: PreparedStatement, i: Int) {
                    ps.setString(1, todoList[i]?.title ?: "test")
                    ps.setString(2, todoList[i]?.content ?: "test")
                    ps.setString(3, todoList[i]?.progress.toString())
                }
                override fun getBatchSize() = todoList.size
            }).size
    }
}