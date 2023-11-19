package study.yapp.todolist.week2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import study.yapp.todolist.week2.dao.Item;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = false)
    public void saveAll(List<Item> items) {
        String sql = "INSERT INTO item (title, contents, created_date, updated_date, member_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        Item item = items.get(i);
                        ps.setString(1, item.getTitle());
                        ps.setString(2, item.getContents());
                        ps.setDate(3, new Date(item.getCreated_date().getTime()));
                        ps.setDate(4, new Date(item.getUpdated_date().getTime()));
                        ps.setLong(5, item.getMember().getId());
                        //                    ps.setLong(6, item.getId());

                    }

                    @Override
                    public int getBatchSize() {
                        return items.size();
                    }
                });
    }
}