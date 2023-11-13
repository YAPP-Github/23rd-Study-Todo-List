package site.yapp.study.todolist.api.todo.repository;

import org.springframework.stereotype.Repository;
import site.yapp.study.todolist.api.todo.domain.Todo;
import site.yapp.study.todolist.common.exception.NotFoundException;
import site.yapp.study.todolist.common.response.ErrorCode;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TodoRepository {

    private final Map<Long, Todo> todoList = new HashMap<>();

    private Todo findByIdOrThrow(Long id) {

        Todo todo;
        try {
            todo = todoList.get(id);
        } catch (Exception e) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_TODO);
        }

        return todo;
    }

    public void save(Todo todo) {
        todoList.put(todo.getId(), todo);
    }
}
