package yapp.study.todolist.domain.bulk.service

import org.springframework.stereotype.Service
import yapp.study.todolist.common.const.TodoConst
import yapp.study.todolist.domain.bulk.enums.EntitySort
import yapp.study.todolist.domain.category.entity.Category
import yapp.study.todolist.domain.category.repository.CategoryRepository
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

@Service
class BulkService(
        val categoryRepository: CategoryRepository
) {
    fun bulkInsert() {
        val categories = toCategories(readLines(EntitySort.CATEGORY))
        categoryRepository.bulkSave(categories)

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
            EntitySort.CATEGORY -> TodoConst.RESOURCES_PATH + "/bulkData/category.txt"
            EntitySort.COMMENT -> TodoConst.RESOURCES_PATH + "/bulkData/comment.txt"
            EntitySort.TODO -> TodoConst.RESOURCES_PATH + "/bulkData/todo.txt"
        }
        val fileReader = FileReader(File(path))
        val bufferReader = BufferedReader(fileReader)

        val lines = mutableListOf<String>()
        bufferReader.useLines { list -> list.forEach { lines.add(it) } }
        return lines
    }

}