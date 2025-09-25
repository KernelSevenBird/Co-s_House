package com.bird.cos.repository.post;

import com.bird.cos.domain.post.Post;
import com.bird.cos.service.home.dto.HomePostDto;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post,Long>
{

        @Query("select new com.bird.cos.service.home.dto.HomePostDto(p.postId, p.title, p.likeCount, p.commentCount) " +
                "from Post p where p.isPublic = true order by p.likeCount desc, p.viewCount desc")
        List<HomePostDto> findTopPublic(Pageable pageable);

        // 조회수 증가
        @Modifying
        @Transactional
        @Query("UPDATE Post p SET p.viewCount = p.viewCount + 1 WHERE p.postId = :postId")
        void increaseViewCount(@Param("postId") Long postId);

        //좋아요 증가
        @Modifying
        @Transactional
        @Query("UPDATE Post p SET p.likeCount = p.likeCount + 1 WHERE p.postId = :postId")
        void increaseLikeCount(@Param("postId") Long postId);

        //카테고리별 조회
        List<Post> findAllByPostCategory_PostCategoryId(Long categoryId);

        // 전체 게시글 정렬
        List<Post> findAllByOrderByPostCreatedAtDesc(); // 최신순
        List<Post> findAllByOrderByPostCreatedAtAsc();  // 오래된순
        List<Post> findAllByOrderByViewCountDesc();     // 조회수 내림차순
        List<Post> findAllByOrderByLikeCountDesc();     // 좋아요 내림차순

        // 카테고리별 조회 및 정렬
        List<Post> findByPostCategoryPostCategoryIdOrderByPostCreatedAtDesc(Long categoryId);
        List<Post> findByPostCategoryPostCategoryIdOrderByPostCreatedAtAsc(Long categoryId);
        List<Post> findByPostCategoryPostCategoryIdOrderByViewCountDesc(Long categoryId);
        List<Post> findByPostCategoryPostCategoryIdOrderByLikeCountDesc(Long categoryId);

}

