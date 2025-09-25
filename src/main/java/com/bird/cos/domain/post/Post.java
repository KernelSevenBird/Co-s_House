package com.bird.cos.domain.post;

import com.bird.cos.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "POST")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_category_id", nullable = true)
    private PostCategory postCategory;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "like_count")
    private Integer likeCount = 0;

    @Column(name = "view_count")
    private Integer viewCount = 0;

    @Column(name = "comment_count")
    private Integer commentCount = 0;

    @Column(name = "is_public")
    private Boolean isPublic = true;

    @Column(name = "report_count")
    private Integer reportCount = 0;

    @Column(name = "post_created_at", insertable = false, updatable = false)
    private LocalDateTime postCreatedAt;

    @Column(name = "post_updated_at", insertable = false, updatable = false)
    private LocalDateTime postUpdatedAt;

    @Column(name = "post_updated_by")
    private Long postUpdatedBy;

    public void updatePost(PostCategory postCategory,String title,String content,boolean isPublic)
    {
        this.postCategory = postCategory;
        this.title = title;
        this.content = content;
        this.isPublic = isPublic;
    }

}