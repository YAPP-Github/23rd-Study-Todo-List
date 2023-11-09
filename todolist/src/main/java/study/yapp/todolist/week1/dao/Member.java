package study.yapp.todolist.week1.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Member {
    private Long member_id;
    private String name;
    private String email;
    private String password;

}