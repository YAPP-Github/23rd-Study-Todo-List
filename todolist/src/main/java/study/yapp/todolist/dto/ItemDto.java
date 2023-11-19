package study.yapp.todolist.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

public class ItemDto {

    @Getter
    public static class RequestItemDto {
        private String title;
        private String contents;
        private Long memberId;
    }

    @Getter
    public static class RequestUpdateItemDto {
        private String title;
        private String contents;
        private Long memberId;
    }

    @Builder
    @Data
    public static class ResponseItemDto {
        private Long itemId;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date createdDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date updatedDate;
        private String title;
        private String contents;
    }

    @Builder
    @Data
    public static class ResponseDeleteItemDto {
        private Long itemId;
        private String title;
        private String contents;
    }

    @Builder
    @Data
    public static class ResponseBulkItem {
        private Long memberId;
        private Long count;
    }

}