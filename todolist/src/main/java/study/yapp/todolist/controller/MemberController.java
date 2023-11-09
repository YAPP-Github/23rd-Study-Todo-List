package study.yapp.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import study.yapp.todolist.config.Response;
import study.yapp.todolist.dto.MemberDto;
import study.yapp.todolist.week1.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 api
     * @param signUpDto
     * @return
     */
    @PostMapping("/signup")
    public Response<MemberDto.ResponseMemberDto> signUp(@RequestBody MemberDto.RequestSignUpDto signUpDto) {
        MemberDto.ResponseMemberDto result = memberService.signUp(signUpDto);

        return new Response<>(result);
    }

    /**
     * 로그인 api
     * @param signInDto
     * @return
     */
    @PostMapping("/signin")
    public Response<MemberDto.ResponseMemberDto> signIn(@RequestBody MemberDto.RequestSignInDto signInDto) {
        MemberDto.ResponseMemberDto result = memberService.signIn(signInDto);

        return new Response<>(result);
    }
}