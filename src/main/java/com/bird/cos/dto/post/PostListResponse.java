package com.bird.cos.dto.post;


import com.bird.cos.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponse
{

    private Long postId;
    private String title;
    private String userNickname;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private LocalDateTime createdAt;

    public static PostListResponse from(Post post)
    {
        return new PostListResponse(
                post.getPostId(),
                post.getTitle(),
                post.getUser().getUserNickname(),
                post.getLikeCount(),
                post.getViewCount(),
                post.getCommentCount(),
                post.getPostCreatedAt()
        );
    }//게시글 목록
}
