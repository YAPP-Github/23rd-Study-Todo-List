package study.yapp.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.yapp.todolist.common.Response;
import study.yapp.todolist.dto.MemberDto;
import study.yapp.todolist.week1.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입 api
     * @param signUpDto
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<Response<MemberDto.ResponseMemberDto>> signUp(@RequestBody MemberDto.RequestSignUpDto signUpDto) {
        MemberDto.ResponseMemberDto result = memberService.signUp(signUpDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new Response<>(result));
    }

    /**
     * 로그인 api
     * @param signInDto
     * @return
     */
    @PostMapping("/signin")
    public ResponseEntity<Response<MemberDto.ResponseMemberDto>> signIn(@RequestBody MemberDto.RequestSignInDto signInDto) {
        MemberDto.ResponseMemberDto result = memberService.signIn(signInDto);

        return ResponseEntity.status(HttpStatus.OK).body(new Response<>(result));
    }
}