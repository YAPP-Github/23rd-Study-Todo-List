package site.yapp.study.todolist.api.todo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import site.yapp.study.todolist.api.todo.dto.request.TodoCreateRequestDto;
import site.yapp.study.todolist.common.exception.BadRequestException;
import site.yapp.study.todolist.common.exception.InternalServerException;
import site.yapp.study.todolist.common.response.ErrorCode;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoBulkRepository {
    @Value("${batchSize}")
    private int batchSize;

    private final JdbcTemplate jdbcTemplate;

    public void saveAll(List<TodoCreateRequestDto> todos) {
        int batchCount = 0;
        List<TodoCreateRequestDto> todoList = new ArrayList<>();

        for (int i = 0; i < todos.size(); i++) {
            todoList.add(todos.get(i));
            if ((i + 1) % batchSize == 0) {
                batchCount = batchInsert(batchCount, todoList);
            }
        }

        if (!todoList.isEmpty()) {
            batchInsert(batchCount, todoList);
        }
    }

    private int batchInsert(int batchCount, List<TodoCreateRequestDto> todoList) {
        String sql = "INSERT INTO TODO (category, content, is_completed) VALUES (?, ?, ?)";

        try {
            jdbcTemplate.batchUpdate(sql,
                    new BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setString(1, todoList.get(i).getCategory());
                            ps.setString(2, todoList.get(i).getContent());
                            ps.setBoolean(3, false);
                        }
                        @Override
                        public int getBatchSize() {
                            return todoList.size();
                        }
                    });
        } catch (Exception e) {
            throw new BadRequestException(ErrorCode.FAILED_BULK_TODO);
        }

        todoList.clear();
        batchCount++;
        return batchCount;
    }
}
