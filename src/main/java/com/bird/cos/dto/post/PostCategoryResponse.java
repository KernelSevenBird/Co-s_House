package com.bird.cos.dto.post;

import com.bird.cos.domain.post.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostCategoryResponse
{
    private Long postCategoryId;//id
    private String postCategoryName;//이름
    private String iconUrl;//아이콘

    public static PostCategoryResponse from(PostCategory postCategory)
    {
        return new PostCategoryResponse(
                postCategory.getPostCategoryId(),
                postCategory.getPostCategoryName(),
                postCategory.getIconUrl()
        );
    }//게시글 목록
}//카테고리 응답 DTO
