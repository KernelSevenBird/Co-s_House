package com.bird.cos.dto.post;

import com.bird.cos.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PostResponse
{
    private Long postId;//postId
    private String title;//제목
    private String content;//내용
    private String categoryName;//카테고리 이름
    private String userNickname;//닉네임
    private Integer likeCount;//좋아요
    private Integer viewCount;//조회수
    private Integer commentCount;//댓글수
    private Boolean isPublic;//공개여부
    private LocalDateTime createdAt;//생성시간
    private LocalDateTime updatedAt;//수정시간

    public static PostResponse from(Post post)
    {
        return new PostResponse
                (
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getPostCategory().getPostCategoryName(),
                post.getUser().getUserNickname(),
                post.getLikeCount(),
                post.getViewCount(),
                post.getCommentCount(),
                post.getIsPublic(),
                post.getPostCreatedAt(),
                post.getPostUpdatedAt()
        );
    }//entity -> dto


}//상세 view
