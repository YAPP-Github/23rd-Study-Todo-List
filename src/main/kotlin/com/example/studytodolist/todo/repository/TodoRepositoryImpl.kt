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
        for (key in todoMap.keys){
            var valueMap: HashMap<String, Any> = todoMap.getValue(key) as HashMap<String, Any>
            todoList.add(Todo(key, valueMap.getValue("content") as String, valueMap.getValue("progress") as Progress))
        }
        return todoList;
    }

    override fun deleteByTitle(title: String) {
        todoMap.remove(title)
    }

    @Suppress("UNCHECKED_CAST")
    override fun updateProgress(title: String, progress: Progress): Todo {
        var valueMap: HashMap<String, Any> = todoMap.getValue(title) as HashMap<String, Any>
        valueMap["progress"] = progress
        todoMap[title] = valueMap
        return Todo(title, valueMap["content"] as String, progress)
    }
}