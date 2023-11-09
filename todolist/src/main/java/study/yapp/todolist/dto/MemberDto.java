package study.yapp.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class MemberDto {

    @Data
    @AllArgsConstructor
    @Builder
    public static class RequestSignUpDto {
        private String name;
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class RequestSignInDto {
        private String email;
        private String password;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class ResponseMemberDto {
        private Long memberId;
    }
}