package com.bird.cos.dto.post;

import com.bird.cos.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest
{
    private long postId;//id
    private String title;//제목
    private String content;//내용
    private long categoryId;//카테고리
    private Boolean isPublic;//공개여부(공개, 비공개)

    public static PostCreateRequest from(Post post)
    {
        return new PostCreateRequest
                (
                        post.getPostId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getPostCategory().getPostCategoryId(),
                        post.getIsPublic()
                );
    }//entity -> dto
}
