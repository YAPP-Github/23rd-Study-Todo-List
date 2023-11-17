package yapp.study.todolist.domain.todo.repository

import org.springframework.stereotype.Component
import yapp.study.todolist.domain.todo.entity.Todo

@Component
class TodoRepositoryCustomImpl(
        private val todos: MutableMap<Long, Todo> = mutableMapOf()
) : TodoRepositoryCustom {
    override fun saveLocal(todo: Todo): Todo {
        todos[todo.id!!] = todo
        return todo
    }

    override fun findLocalAll(): List<Todo> {
        return todos.values.toList()
    }

    override fun findLocalById(id: Long): Todo? {
        return todos[id]
    }

    override fun deleteLocalById(id: Long) {
        todos.remove(id)
    }

    override fun findLocalByCategoryId(categoryId: Long): List<Todo> {
        return todos.values.filter { it.categoryId == categoryId }
    }

    override fun deleteLocalByIdIn(todoIds: List<Long>) {
        todoIds.map { todos.remove(it) }
    }

    override fun existLocalById(id: Long): Boolean {
        return todos[id] != null
    }

    override fun deleteLocalAll() {
        todos.clear()
    }

    override fun findLocalByIdAndIsDone(id: Long, isDone: Boolean): Todo? {
        return if (todos[id]?.isDone == isDone) {
            todos[id]
        } else
            null
    }
}