package yapp.study.todolist.domain.category.repository

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import yapp.study.todolist.common.const.BULK_SIZE
import yapp.study.todolist.domain.category.entity.Category
import java.time.Instant
import java.sql.Date

@Component
class CategoryRepositoryCustomImpl(
        private val jdbcTemplate: JdbcTemplate
) : CategoryRepositoryCustom {
    private val categories: MutableMap<Long, Category> = mutableMapOf()

    override fun saveLocal(category: Category): Category {
        categories[category.id!!] = category
        return category
    }

    override fun findLocalAll(): List<Category> {
        return categories.values.toList()
    }

    override fun findLocalById(id: Long): Category? {
        return categories[id]
    }

    override fun deleteLocalById(id: Long) {
        categories.remove(id)
    }

    override fun existLocalById(id: Long): Boolean {
        return categories[id] != null
    }

    override fun deleteLocalAll() {
        categories.clear()
    }

    override fun bulkSave(categories: List<Category>) {
        val sql = """
            insert into category(name, created_at, updated_at)
                values (?, ?, ?)
        """.trimIndent()
        jdbcTemplate.batchUpdate(sql, categories, BULK_SIZE) { preparedStatement, category ->
            preparedStatement.setString(1, category.name)
            preparedStatement.setDate(2, Date(Instant.now().toEpochMilli()))
            preparedStatement.setDate(3, Date(Instant.now().toEpochMilli()))
        }
    }

}