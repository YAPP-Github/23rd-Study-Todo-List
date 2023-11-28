package site.yapp.study.todolist.api.todo.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.yapp.study.todolist.api.todo.domain.Todo;
import site.yapp.study.todolist.common.exception.NotFoundException;
import site.yapp.study.todolist.common.response.ErrorCode;

import java.util.Optional;


@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>  {

    default Todo findByIdOrThrow(Long todoId) {

        return findById(todoId).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_TODO)
        );
    }

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT q FROM Todo q WHERE q.id = :todoId")
    Optional<Todo> findByIdForUpdate(@Param("todoId") Long todoId);
}
