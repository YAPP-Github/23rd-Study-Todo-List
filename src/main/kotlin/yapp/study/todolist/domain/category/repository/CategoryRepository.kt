package yapp.study.todolist.domain.category.repository

import org.springframework.data.jpa.repository.JpaRepository
import yapp.study.todolist.domain.category.entity.Category

interface CategoryRepository : JpaRepository<Category, Long>, CategoryRepositoryCustom

interface CategoryRepositoryCustom {
    fun saveLocal(category: Category): Category
    fun findLocalAll(): List<Category>
    fun findLocalById(id: Long): Category?
    fun deleteLocalById(id: Long)
    fun existLocalById(id: Long): Boolean
    fun deleteLocalAll()
    fun bulkSave(categories: List<Category>)
}