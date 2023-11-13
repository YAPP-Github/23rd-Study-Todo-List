package site.yapp.study.todolist.api.todo.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.yapp.study.todolist.api.todo.domain.Todo;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.repository.TodoRepository;
import site.yapp.study.todolist.api.todo.service.TodoService;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    public Long sequence = 1L;
    private final TodoRepository todoRepository;

    @Override
    public void createTodo(TodoCreateRequestDto requestDto) {

        Todo todo = Todo.builder()
                .id(sequence++)
                .category(requestDto.getCategory())
                .content(requestDto.getContent())
                .created_at(new Date())
                .updated_at(new Date())
                .build();

        todoRepository.save(todo);
    }
}
