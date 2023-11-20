package site.yapp.study.todolist.api.todo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.yapp.study.todolist.common.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String category;

    private String content;

    @Column(name = "is_deleted")
    private LocalDateTime deletedAt;

    private boolean isCompleted;

    @Builder
    public Todo(String category, String content) {
        this.category = category;
        this.content = content;
        this.deletedAt = null;
        this.isCompleted = false;
    }

    public void updateTodo(String category, String content) {
        this.category = category;
        this.content = content;
    }

    public void toggleTodo(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
