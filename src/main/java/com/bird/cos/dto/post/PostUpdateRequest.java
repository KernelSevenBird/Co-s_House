package com.bird.cos.dto.post;

import com.bird.cos.domain.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateRequest
{
    private long postCategoryId;
    private String title;//제목
    private String content;//내용
    private Boolean isPublic;//공개여부(공개, 비공개)

    public static PostUpdateRequest from(Post post)
    {
        return new PostUpdateRequest(
             post.getPostCategory().getPostCategoryId(),
             post.getTitle(),
             post.getContent(),
             post.getIsPublic()
        );
    }//게시글 목록
}
