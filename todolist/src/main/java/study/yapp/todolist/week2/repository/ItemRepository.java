package study.yapp.todolist.week2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.yapp.todolist.week2.dao.Item;
import study.yapp.todolist.week2.dao.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findById(Long id);
    List<Item> findAllByMember(Member member);
}