package yapp.study.todolist.domain.category.repository

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.base.PageParam
import yapp.study.todolist.domain.category.entity.Category

@Component
class CategoryRepositoryImpl(
        private val categories: MutableMap<Long, Category> = mutableMapOf()
) : CategoryRepository {
    override fun save(category: Category): Category {
        categories[category.id] = category
        return category
    }

    override fun findAll(): List<Category> {
        return categories.values.toList()
    }

    override fun findById(id: Long): Category? {
        return categories[id]
    }

    override fun deleteById(id: Long) {
        categories.remove(id)
    }

    override fun existById(id: Long): Boolean {
        return categories[id] != null
    }

    override fun deleteAll() {
        categories.clear()
    }

}