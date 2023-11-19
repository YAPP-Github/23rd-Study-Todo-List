package study.yapp.todolist.week2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.yapp.todolist.week2.dao.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndPassword(String email, String password);
    Optional<Member> findById(Long id);
}