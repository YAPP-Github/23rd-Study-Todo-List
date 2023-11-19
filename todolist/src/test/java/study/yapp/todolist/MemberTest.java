package study.yapp.todolist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.yapp.todolist.dto.MemberDto;
import study.yapp.todolist.week1.dao.Member;
import study.yapp.todolist.week1.repository.MemberRepository;
import study.yapp.todolist.week1.service.MemberService;

public class MemberTest {


    @Test
    @DisplayName("회원가입 테스트")
    void signupSuccess() {
        MemberRepository memberRepository = new MemberRepository();
        MemberService memberService = new MemberService(memberRepository);

        Member member = Member.builder()
                .member_id(1L)
                .email("aaa@gmail.com")
                .password("aaapw")
                .build();

        MemberDto.ResponseMemberDto result = memberService.signUp(new MemberDto.RequestSignUpDto(member.getName(), member.getEmail(), member.getPassword()));

        Assertions.assertThat(result.getMemberId()).isEqualTo(member.getMember_id());
    }
}