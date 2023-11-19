package study.yapp.todolist.week1.dao;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Item {
    private Long item_id;
    private Long member_id;
    private String title;
    private String contents;
    private Date created_date;
    private Date updated_date;
}