package com.example.studytodolist.todo.repository

import com.example.studytodolist.todo.domain.Progress
import com.example.studytodolist.todo.domain.Todo

class TodoRepositoryImpl: TodoRepository {
    companion object{
        private var todoMap = HashMap<String, Any>()
    }
    override fun save(todo: Todo): Todo {
        var valueMap = HashMap<String, Any>()
        valueMap["content"] = todo.content
        valueMap["progress"] = todo.progress
        todoMap[todo.title] = valueMap
        return todo
    }

    @Suppress("UNCHECKED_CAST")
    override fun findAll(): List<Todo> {
        var todoList = mutableListOf<Todo>()
        for ((key, value) in todoMap){
            var valueMap: HashMap<String, Any> = todoMap.getValue(key) as HashMap<String, Any>
            todoList.add(Todo(key, valueMap.getValue("content") as String, valueMap.getValue("progress") as Progress))
        }
        return todoList;
    }

    override fun deleteByTitle(title: String) {
        todoMap.remove(title)
    }

    override fun update(title: String, content: String, progress: Progress): Todo {
        var valueMap = HashMap<String, Any>()
        valueMap["content"] = content
        valueMap["progress"] = progress
        todoMap[title] = valueMap
        return Todo(title, content, progress)
    }
}