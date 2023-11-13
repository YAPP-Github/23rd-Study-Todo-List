package yapp.study.todolist.domain.todo.repository

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.todo.entity.Todo

@Component
class TodoRepositoryImpl(
        private val todos: MutableMap<Long, Todo> = mutableMapOf()
) : TodoRepository {
    override fun save(todo: Todo): Todo {
        todos[todo.id] = todo
        return todo
    }

    override fun findAll(): List<Todo> {
        return todos.values.toList()
    }

    override fun findById(id: Long): Todo? {
        return todos[id]
    }

    override fun deleteById(id: Long) {
        todos.remove(id)
    }

    override fun findByCategoryId(categoryId: Long): List<Todo> {
        return todos.values.filter { it.categoryId == categoryId }
    }

    override fun deleteByIdIn(todoIds: List<Long>) {
        todoIds.map { todos.remove(it) }
    }

    override fun existById(id: Long): Boolean {
        return todos[id] != null
    }

    override fun deleteAll() {
        todos.clear()
    }
}