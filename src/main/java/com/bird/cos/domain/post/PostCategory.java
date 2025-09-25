package com.bird.cos.domain.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "POST_CATEGORY")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_category_id")
    private Long postCategoryId;

    @Column(name = "post_category_name", length = 100, nullable = false)
    private String postCategoryName;

    @Column(name = "post_category_type", length = 50, nullable = false)
    private String postCategoryType;

    @Column(name = "icon_url", length = 500)
    private String iconUrl;

    @Column(name = "display_order")
    private Integer displayOrder = 0;

    public void updatePostCategory(String postCategoryName,String postCategoryType,String iconUrl,Integer displayOrder)
    {
        this.postCategoryName = postCategoryName;
        this.postCategoryType = postCategoryType;
        this.iconUrl = iconUrl;
        this.displayOrder = displayOrder;
    }


}