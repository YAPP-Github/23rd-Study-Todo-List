package yapp.study.todolist.domain.category.repository

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.category.entity.Category

@Component
class CategoryRepositoryCustomImpl(
        private val categories: MutableMap<Long, Category> = mutableMapOf()
) : CategoryRepositoryCustom {
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

}