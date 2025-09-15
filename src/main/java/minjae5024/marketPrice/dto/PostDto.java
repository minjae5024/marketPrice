package minjae5024.marketPrice.dto;

import minjae5024.marketPrice.entity.Post;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

public class PostDto {

    @Getter
    @Setter
    public static class Request {
        private Long marketId;

        @Size(max = 100, message = "제목은 100자 이내로 작성해주세요.")
        private String title;

        @Size(max = 500, message = "내용은 500자 이내로 작성해주세요.")
        private String content;
    }

    @Getter
    public static class ListResponse { 
        private Long id;
        private String title;
        private String author;
        private String createdAt;
        private int viewCount;
        private boolean isOwner;

        public ListResponse(Post post, boolean isOwner) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.author = post.getUser().getUsername();
            this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.viewCount = post.getViewCount();
            this.isOwner = isOwner;
        }
    }

    @Getter
    public static class DetailResponse { 
        private Long id;
        private String title;
        private String content;
        private String author;
        private String createdAt;
        private int viewCount;
        private boolean isOwner;

        public DetailResponse(Post post, boolean isOwner) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.author = post.getUser().getUsername();
            this.createdAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.viewCount = post.getViewCount();
            this.isOwner = isOwner;
        }
    }
}
