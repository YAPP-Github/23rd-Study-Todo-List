package yapp.study.todolist.domain.todo.repository

import org.springframework.data.jpa.repository.JpaRepository
import yapp.study.todolist.domain.todo.entity.Todo

interface TodoRepository : JpaRepository<Todo, Long>, TodoRepositoryCustom{
    fun findByCategoryId(categoryId: Long): List<Todo>
    fun deleteAllByIdIn(ids: List<Long>)
    fun findByIdAndIsDone(id: Long, isDone: Boolean): Todo?
}

interface TodoRepositoryCustom {
    fun saveLocal(todo: Todo): Todo
    fun findLocalAll(): List<Todo>
    fun findLocalById(id: Long): Todo?
    fun deleteLocalById(id: Long)
    fun findLocalByCategoryId(id: Long): List<Todo>
    fun deleteLocalByIdIn(todoIds: List<Long>)
    fun existLocalById(id: Long): Boolean
    fun deleteLocalAll()
    fun findLocalByIdAndIsDone(id: Long, isDone: Boolean): Todo?
}