package site.yapp.study.todolist.api.todo.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.yapp.study.todolist.api.todo.domain.Todo;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.request.TodoUpdateRequestDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoGetResponseDto;
import site.yapp.study.todolist.api.todo.repository.TodoRepository;
import site.yapp.study.todolist.api.todo.service.TodoService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    public Long INDEX = 1L;
    private final TodoRepository todoRepository;

    @Override
    public void createTodo(TodoCreateRequestDto requestDto) {

        Todo todo = Todo.builder()
                .id(INDEX++)
                .category(requestDto.getCategory())
                .content(requestDto.getContent())
                .created_at(new Date())
                .updated_at(new Date())
                .build();

        todoRepository.save(todo);
    }


    @Override
    public List<TodoGetResponseDto> getAllTodo() {

        return todoRepository.findAll().stream()
                .map(todo -> TodoGetResponseDto.of(todo.getId(), todo.getCategory(), todo.getContent(), todo.is_completed()))
                .collect(Collectors.toList());
    }

    @Override
    public TodoGetResponseDto getEachTodo(Long todoId) {

        Todo todo = todoRepository.findByIdOrThrow(todoId);

        return TodoGetResponseDto.of(todo);
    }

    @Override
    public void updateTodo(Long todoId, TodoUpdateRequestDto requestDto) {

        Todo todo = todoRepository.findByIdOrThrow(todoId);

        todo.updateTodo(requestDto.getCategory(), requestDto.getContent());
    }
}
