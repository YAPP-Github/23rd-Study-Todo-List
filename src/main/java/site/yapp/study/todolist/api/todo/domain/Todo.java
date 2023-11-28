package site.yapp.study.todolist.api.todo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.repository.Lock;
import site.yapp.study.todolist.common.domain.BaseEntity;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

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

    @ColumnDefault("0")
    private Integer viewCount = 0;

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

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("UPDATE Todo t SET t.viewCount = t.viewCount + 1 WHERE t.id = :todoId")
    public void updateViewCount() {
        viewCount++;
    }
}
