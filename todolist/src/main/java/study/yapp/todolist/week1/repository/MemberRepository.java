package study.yapp.todolist.week1.repository;

import org.springframework.stereotype.Repository;
import study.yapp.todolist.week1.dao.Member;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberRepository {
    public AtomicLong MEMBER_INDEX = new AtomicLong(0);

    private final ConcurrentHashMap<String, Member> memberList = new ConcurrentHashMap<>();

    public void saveMember(Member member) {
        memberList.put(member.getEmail(), member);
    }

    public Optional<Member> findByEmail(String email) {
        Optional<Member> result = Optional.ofNullable(memberList.get(email));

        return result;
    }

    public Optional<Member> findByEmailAndPassword(String email, String password) {
        Optional<Member> result = Optional.ofNullable(memberList.get(email));
        if (!result.get().getPassword().equals(password)) {
            result = Optional.ofNullable(null);
        }
        return result;
    }

}