package yapp.study.todolist.domain.category.repository

import yapp.study.todolist.domain.category.entity.Category

interface CategoryRepository {
    fun save(category: Category): Category
    fun findAll(): List<Category>
    fun findById(id: Long): Category?
    fun deleteById(id: Long)
    fun existById(id: Long): Boolean
    fun deleteAll()
}