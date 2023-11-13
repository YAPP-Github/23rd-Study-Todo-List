package site.yapp.study.todolist.api.todo.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yapp.study.todolist.api.todo.domain.Todo;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.repository.TodoRepository;
import site.yapp.study.todolist.api.todo.service.TodoService;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    @Override
    @Transactional
    public void createTodo(TodoCreateRequestDto requestDto) {

        Todo todo = Todo.builder()
                .category(requestDto.getCategory())
                .content(requestDto.getContent())
                .build();

        todoRepository.save(todo);
    }
}
