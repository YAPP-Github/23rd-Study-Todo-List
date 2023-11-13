package site.yapp.study.todolist.api.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.yapp.study.todolist.api.todo.domain.Todo;
import site.yapp.study.todolist.common.exception.NotFoundException;
import site.yapp.study.todolist.common.response.ErrorCode;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    default Todo findByIdOrThrow(Long todoId) {

        return findById(todoId).orElseThrow(
                () -> new NotFoundException(ErrorCode.NOT_FOUND_TODO)
        );
    }
}
