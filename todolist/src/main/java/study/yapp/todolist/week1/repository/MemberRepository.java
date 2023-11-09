package study.yapp.todolist.week1.repository;

import org.springframework.stereotype.Repository;
import study.yapp.todolist.week1.dao.Member;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberRepository {
    public Long MEMBER_INDEX = 1L;

    private final Map<String, Member> memberList = new HashMap<>();

    public void saveMember(Member member) {
        memberList.put(member.getEmail(), member);
    }

    public Member findByEmail(String email) {
        Member result = null;
        try {
            result = memberList.get(email);
        } catch (Exception e){
            return null;
        }
        return result;
    }

    public Member findByEmailAndPassword(String email, String password) {
        Member result = null;
        try {
            result = memberList.get(email);
            if (!result.getPassword().equals(password)) {
                throw new RuntimeException("로그인 실패");
            }
        } catch (Exception e){
            return null;
        }
        return result;
    }
}