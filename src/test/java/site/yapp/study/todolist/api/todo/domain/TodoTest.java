package site.yapp.study.todolist.api.todo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import site.yapp.study.todolist.TodolistApplication;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.api.todo.repository.TodoBulkRepository;
import site.yapp.study.todolist.api.todo.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@ContextConfiguration(classes = TodolistApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
public class TodoTest {
    private static final int COUNT = 10_000;
    @Autowired
    private TodoRepository todoRepository;

    @SpyBean
    private TodoBulkRepository todoBulkRepository;

    @BeforeAll
    void init() {
        Todo todo = new Todo("초기", "테스트");
        todoRepository.save(todo);
    }


    @DisplayName("할일 저장 테스트")
    @Test
    void successSaveTodo() {
        // given
        Todo todo = Todo.builder()
                .category("Test Todo")
                .content("Test Content")
                .build();

        // when
        todoRepository.save(todo);

        // then
        Todo savedTodo = todoRepository.findByIdOrThrow(todo.getId());
        assertThat(savedTodo.getCategory()).isEqualTo("Test Todo");
        assertThat(savedTodo.getContent()).isEqualTo("Test Content");
        assertThat(savedTodo.isCompleted()).isFalse();
    }

    @Test
    @DisplayName("단건 save로 할일 생성 테스트")
    void successNormalCreateTodo() {
        long startTime = System.currentTimeMillis();

        for (long i = 2; i < COUNT; i++) {
            Todo todo = Todo.builder()
                    .category("Test Todo" + i)
                    .content("Test Content" + i)
                    .build();
            todoRepository.save(todo);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("---------------------------------");
        System.out.printf("수행시간: %d\n", endTime - startTime);
        System.out.println("---------------------------------");
    }

    @Test
    @DisplayName("벌크 할일 생성 테스트")
    void successBulkCreateTodo() {
        long startTime = System.currentTimeMillis();
        List<TodoCreateRequestDto> todoList = new ArrayList<>();

        for (long i = 0; i < COUNT; i++) {
            TodoCreateRequestDto todo = TodoCreateRequestDto.builder()
                    .category("Test Todo" + i)
                    .content("Test Content" + i)
                    .build();
            todoList.add(todo);
        }

        todoBulkRepository.saveAll(todoList);
        long endTime = System.currentTimeMillis();
        System.out.println("---------------------------------");
        System.out.printf("수행시간: %d\n", endTime - startTime);
        System.out.println("---------------------------------");
    }
}
