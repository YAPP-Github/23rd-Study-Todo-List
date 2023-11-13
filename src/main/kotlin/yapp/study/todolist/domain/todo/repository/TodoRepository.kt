package yapp.study.todolist.domain.todo.repository

import yapp.study.todolist.domain.todo.entity.Todo

interface TodoRepository {
    fun save(todo: Todo): Todo
    fun findAll(): List<Todo>
    fun findById(id: Long): Todo?
    fun deleteById(id: Long)
    fun findByCategoryId(id: Long): List<Todo>
    fun deleteByIdIn(todoIds: List<Long>)
    fun existById(id: Long): Boolean
    fun deleteAll()
}