package study.yapp.todolist.common;

import lombok.Getter;

@Getter
public enum ResponseCode {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000: USER
     */

    // member
    // 로그인
    INVALID_USER(false,2012,"존재하지 않는 유저입니다."),
    DUPLICATE_USER(false, 2013, "이미 존재하는 유저 정보입니다."),

    /**
     * 3000 : ITEM
     */
    INVALID_ITEM(false, 3001, "존재하지 않는 항목입니다."),

    INVALID_USER_ACCESS(false, 3002, "잘못된 유저의 삭제 시도입니다."),
    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private ResponseCode(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}