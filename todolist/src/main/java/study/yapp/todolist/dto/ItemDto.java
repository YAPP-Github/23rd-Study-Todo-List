package study.yapp.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class ItemDto {
    @Data
    @AllArgsConstructor
    @Builder
    public static class RequestItemDto {
        private String title;
        private String contents;
        private Long memberId;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class RequestUpdateItemDto {
        private String title;
        private String contents;
        private Long memberId;
        private Long itemId;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class RequestDeleteDto {
        private Long itemId;
        private Long memberId;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class ResponseItemDto {
        private Long itemId;
        private String createdDate;
        private String updatedDate;
        private String title;
        private String contents;
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class ResponseDeleteItemDto {
        private Long itemId;
        private String title;
        private String contents;
    }

}