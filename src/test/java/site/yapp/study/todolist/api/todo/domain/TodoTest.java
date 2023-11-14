package site.yapp.study.todolist.api.todo.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import site.yapp.study.todolist.api.todo.repository.TodoRepository;

import java.util.Date;

public class TodoTest {
    @Autowired
    private TodoRepository todoRepository;

    @DisplayName("할일 저장 테스트")
    @Test
    void successSaveTodo() {
        // given
        Todo todo = Todo.builder()
                .id(1L)
                .category("Test Todo")
                .content("Test Content")
                .created_at(new Date())
                .updated_at(new Date())
                .build();

        // when
        todoRepository.save(todo);

        // then
        Todo savedTodo = todoRepository.findByIdOrThrow(todo.getId());
        assertThat(savedTodo.getId()).isEqualTo(1L);
        assertThat(savedTodo.getCategory()).isEqualTo("Test Todo");
        assertThat(savedTodo.getContent()).isEqualTo("Test Content");
        assertThat(savedTodo.is_completed()).isFalse();
    }
}
