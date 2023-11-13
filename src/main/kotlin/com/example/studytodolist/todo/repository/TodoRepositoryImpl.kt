package com.example.studytodolist.todo.repository

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicLong

@Component
class TodoRepositoryImpl: TodoRepository {
    companion object{
        private var todoMap = HashMap<Long, Todo>()
        var INDEX = AtomicLong()
    }
    override fun save(todo: Todo): Todo {
        todoMap[todo.id] = todo
        return todo
    }

    override fun findAll(): List<Todo> {
        return todoMap.values.toList()
    }

    override fun findByIdOrNull(id: Long): Todo? {
        return todoMap[id]
    }

    override fun deleteById(id: Long) {
        todoMap.remove(id)
    }

    override fun updateProgress(id: Long, progress: Progress): Todo {
        val todo: Todo = todoMap[id] ?: throw RuntimeException("존재하지 않는 todo입니다.")
        todo.progress = progress
        return todo
    }
}