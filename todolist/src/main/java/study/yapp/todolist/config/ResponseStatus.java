package study.yapp.todolist.config;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),
    METHOD_NOT_ALLOWED(false,2004,"http method를 확인해주세요"),

    // member
    // 로그인
    USERS_EMPTY_USER_EMAIL(false, 2010, "유저 이메일 값을 확인해주세요."),
    USERS_EMPTY_USER_PASSWORD(false,2011,"유저 비밀번호 값을 확인해주세요"),
    USER_EMPTY(false,2012,"존재하지 않는 유저입니다."),

    // 회원가입

    // post
    // 게시글 삭제 - 설문지가 존재해서 게시글 삭제 불가
    SURVEY_EXIST(false,2020,"설문지가 존재하여 게시글 삭제가 불가합니다. 설문지를 먼저 삭제해주세요."),
    //게시글 삭제 - 설문지 삭제 시도 유저가 잘못됨.
    DELETE_INVALID_USER(false,2021,"잘못된 유저가 게시글 삭제를 시도했습니다."),

    //설문지 삭제 - 게시글이 존재해서 설문지 삭제 불가
    POST_EXIST(false, 2022, "게시글이 존재하여 설문지 삭제가 불가합니다. 게시글을 먼저 삭제해주세요"),



    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,3014,"없는 아이디거나 비밀번호가 틀렸습니다."),




    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");





    private final boolean isSuccess;
    private final int code;
    private final String message;

    private ResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}