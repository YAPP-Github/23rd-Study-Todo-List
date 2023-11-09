package study.yapp.todolist.week1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.yapp.todolist.week1.repository.MemberRepository;
import study.yapp.todolist.week1.dao.Member;
import study.yapp.todolist.dto.MemberDto;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param request
     * @return
     */
    public MemberDto.ResponseMemberDto signUp(MemberDto.RequestSignUpDto request) {
        if (duplicatedMember(request)) {
            throw new RuntimeException("중복된 유저");
        }

        Member member = Member.builder()
                .member_id(memberRepository.MEMBER_INDEX++)
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .build();

        memberRepository.saveMember(member);

        MemberDto.ResponseMemberDto result = MemberDto.ResponseMemberDto.builder()
                                                                        .memberId(member.getMember_id())
                                                                        .build();

        return result;
    }

    /**
     * 로그인
     * @param request
     * @return
     */
    public MemberDto.ResponseMemberDto signIn(MemberDto.RequestSignInDto request) {
        Member member = memberRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

        if (member == null) {
            throw new RuntimeException("로그인 실패");
        }

        MemberDto.ResponseMemberDto result = MemberDto.ResponseMemberDto.builder()
                .memberId(member.getMember_id())
                .build();

        return result;
    }

    private boolean duplicatedMember(MemberDto.RequestSignUpDto request) {
        Member result = memberRepository.findByEmail(request.getEmail());
        if (result != null) {
            return true;
        }
        return false;
    }
}