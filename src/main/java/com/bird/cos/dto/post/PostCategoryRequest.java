package com.bird.cos.dto.post;

import com.bird.cos.domain.post.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCategoryRequest
{
    private String postCategoryName;//이름
    private String postCategoryType;//타입
    private String iconUrl;//아이콘
    private Integer displayOrder;

    public static PostCategoryRequest from(PostCategory postCategory)
    {
        return new PostCategoryRequest
                (
                        postCategory.getPostCategoryName(),
                        postCategory.getPostCategoryType(),
                        postCategory.getIconUrl(),
                        postCategory.getDisplayOrder()
                );
    }//entity -> dto
}//카테고리 등록/수정 관리자용
