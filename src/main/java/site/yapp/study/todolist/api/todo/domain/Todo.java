package site.yapp.study.todolist.api.todo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class Todo {

    private Long id;

    private String category;

    private String content;

    private Date created_at;

    private Date updated_at;

    private Date deleted_at;

    private boolean is_completed;

    @Builder
    public Todo(Long id, String category, String content, Date created_at, Date updated_at) {
        this.id = id;
        this.category = category;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = null;
        this.is_completed = false;
    }

    public void updateTodo(String category, String content) {
        this.category= category;
        this.content = content;
    }

    public void toggleTodo(Boolean is_completed) {
        this.is_completed = is_completed;
    }
}
