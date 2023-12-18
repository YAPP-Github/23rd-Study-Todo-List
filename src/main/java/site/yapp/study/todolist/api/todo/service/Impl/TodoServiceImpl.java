package site.yapp.study.todolist.api.todo.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.yapp.study.todolist.api.todo.controller.TodoController;
import site.yapp.study.todolist.api.todo.domain.Todo;
import site.yapp.study.todolist.api.todo.dto.request.TodoBulkCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.dto.request.TodoUpdateRequestDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoGetResponseDto;
import site.yapp.study.todolist.api.todo.dto.response.TodoToggleGetResponseDto;
import site.yapp.study.todolist.api.todo.repository.TodoBulkRepository;
import site.yapp.study.todolist.api.todo.repository.TodoRepository;
import site.yapp.study.todolist.api.todo.service.TodoService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoBulkRepository todoBulkRepository;

    @Override
    @Transactional
    public void createTodo(TodoCreateRequestDto requestDto) {

        Todo todo = Todo.builder()
                .category(requestDto.getCategory())
                .content(requestDto.getContent())
                .build();

        todoRepository.save(todo);
    }

    @Override
    @Transactional
    public void createBulkTodo(TodoBulkCreateRequestDto requestDto) {

        todoBulkRepository.saveAll(requestDto.getTodoList());
    }


    @Override
    public CollectionModel<EntityModel<TodoGetResponseDto>> getAllTodo() {

        List<EntityModel<TodoGetResponseDto>> todos = todoRepository.findAll().stream().map(this::getEntityModel).collect(Collectors.toList());

        return CollectionModel.of(todos,
                linkTo(methodOn(TodoController.class).getAllTodo()).withSelfRel()
        );
    }

    @Override
    @Transactional
    public EntityModel<TodoGetResponseDto> getEachTodo(Long todoId) {

        Todo todo = todoRepository.findByIdOrThrow(todoId);
        Todo todoView = todoRepository.findByIdForUpdate(todoId).orElseThrow(RuntimeException::new);

        todoView.updateViewCount();

        return getEntityModel(todo);
    }

    private EntityModel<TodoGetResponseDto> getEntityModel(Todo todo) {
        EntityModel<TodoGetResponseDto> entityModel = EntityModel.of(TodoGetResponseDto.of(todo.getId(), todo.getCategory(), todo.getContent(), todo.isCompleted(), todo.getViewCount()));
        entityModel.add(Link.of("/todos/"+todo.getId(), "self"));
        entityModel.add(Link.of("/todos", "list"));

        return entityModel;
    }

    @Override
    @Transactional
    public void updateTodo(Long todoId, TodoUpdateRequestDto requestDto) {

        Todo todo = todoRepository.findByIdOrThrow(todoId);

        todo.updateTodo(requestDto.getCategory(), requestDto.getContent());
    }

    @Override
    @Transactional
    public void deleteTodo(Long todoId) {

        Todo todo = todoRepository.findByIdOrThrow(todoId);

        todoRepository.delete(todo);
    }

    @Override
    @Transactional
    public TodoToggleGetResponseDto toggleTodoStatus(Long todoId, Boolean isCompleted) {

        Todo todo = todoRepository.findByIdOrThrow(todoId);

        if (isCompleted) todo.toggleTodo(Boolean.TRUE);
        else todo.toggleTodo(Boolean.FALSE);

        return TodoToggleGetResponseDto.of(todo);
    }
}
