package study.yapp.todolist.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class MemberDto {

    @Getter
    public static class RequestSignUpDto {
        private String name;
        private String email;
        private String password;
    }

    @Getter
    public static class RequestSignInDto {
        private String email;
        private String password;
    }

    @Builder
    @Data
    public static class ResponseMemberDto {
        private Long memberId;
    }
}