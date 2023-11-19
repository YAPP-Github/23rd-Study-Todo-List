package study.yapp.todolist.week2.dao;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private Date created_date;
    private Date updated_date;
    private String title;
    private String contents;

    @Builder
    public Item(Date created_date, String title, String contents) {
        this.contents = contents;
        this.created_date = created_date;
        this.updated_date = created_date;
        this.title = title;
    }

    public void updateItem(Date updated_date, String title, String contents) {
        this.contents = contents;
        this.title = title;
        this.updated_date = updated_date;
    }

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        if (!member.getItemList().contains(this)) {
            member.getItemList().add(this);
        }
    }
}