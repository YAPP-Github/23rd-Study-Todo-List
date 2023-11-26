package yapp.study.todolist.domain.bulk.service

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import yapp.study.todolist.common.const.RESOURCES_PATH
import yapp.study.todolist.common.error.errors.InternalServerException
import yapp.study.todolist.common.extension.toLocalDate
import yapp.study.todolist.common.extension.toLocalTime
import yapp.study.todolist.domain.bulk.enums.EntitySort
import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.category.repository.CategoryRepository
import yapp.study.todolist.domain.comment.entity.Comment
import yapp.study.todolist.domain.comment.repository.CommentRepository
import yapp.study.todolist.domain.todo.entity.Todo
import yapp.study.todolist.domain.todo.repository.TodoRepository
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.IOException

@Service
class BulkService(
        val categoryRepository: CategoryRepository,
        val todoRepository: TodoRepository,
        val commentRepository: CommentRepository,
        val jdbcTemplate: JdbcTemplate
) {
    @Transactional
    fun bulkInsert() {
        println("bulk insert start")
        println("category insert")
        val categories = toCategories(readLines(EntitySort.CATEGORY))
        categoryRepository.bulkSave(categories)

        val lastInsertedCategoryPK = jdbcTemplate.queryForObject("SELECT last_insert_id()", Long::class.java)
        val firstInsertedCategoryPK = lastInsertedCategoryPK!! - categories.count() + 1

        println("todo insert")
        val todos = toTodos(readLines(EntitySort.TODO), firstInsertedCategoryPK)
        todoRepository.bulkSave(todos)

        println("comment insert")
        val lastInsertedTodoPK = jdbcTemplate.queryForObject("SELECT last_insert_id()", Long::class.java)
        val firstInsertedTodoPK = lastInsertedTodoPK!! - categories.count() + 1

        val comments = toComments(readLines(EntitySort.COMMENT), firstInsertedTodoPK)
        commentRepository.bulkSave(comments)
    }

    private fun toComments(lines: List<String>, firstInsertedPK: Long): List<Comment> {
        return lines.map {
            val values = it.split(" ")
            Comment(
                    content = values[0],
                    todoId = values[1].toLong() + firstInsertedPK - 1
            )
        }
    }

    private fun toTodos(lines: List<String>, firstInsertedPk: Long): List<Todo> {
        return lines.map {
            val values = it.split(" ")
            Todo(
                    categoryId = values[0].toLong() + firstInsertedPk - 1,
                    title = values[1],
                    memo = values[2],
                    date = values[3].toLocalDate(),
                    fromTime = values[4].toLocalTime(),
                    toTime = values[5].toLocalTime(),
                    isDone = values[6].toBoolean()
            )
        }
    }

    private fun toCategories(lines: List<String>): List<Category> {
        return lines.map {
            val values = it.split(" ")
            Category(
                    name = values[0]
            )
        }
    }

    private fun readLines(entitySort: EntitySort): List<String> {
        val path = when (entitySort) {
            EntitySort.CATEGORY -> "$RESOURCES_PATH/bulkData/category.txt"
            EntitySort.COMMENT -> "$RESOURCES_PATH/bulkData/comment.txt"
            EntitySort.TODO -> "$RESOURCES_PATH/bulkData/todo.txt"
        }
        try {
            val fileReader = FileReader(File(path))
            val bufferReader = BufferedReader(fileReader)
            return bufferReader.useLines { it.toList() }
        } catch (e: IOException) {
            println(e)
            throw InternalServerException("i/o error")
        }
    }
}
