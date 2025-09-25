package com.bird.cos.service.post;

import com.bird.cos.domain.post.Post;
import com.bird.cos.domain.post.PostCategory;
import com.bird.cos.dto.post.PostCreateRequest;
import com.bird.cos.dto.post.PostUpdateRequest;
import com.bird.cos.repository.post.PostCategoryRepository;
import com.bird.cos.repository.post.PostRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService
{
    private final PostRepository postRepository;
    private final PostCategoryRepository postCategoryRepository;

    // 단일 게시글 조회
    @Transactional(readOnly = true)
    public Post findById(Long postId)
    {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    @Transactional(readOnly = true)
    public List<Post> findAll()
    {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Post> findAllByCategory(Long categoryId)
    {
        return postRepository.findAllByPostCategory_PostCategoryId(categoryId);
    }


    // 게시글 저장
    @Transactional
    public Post save(PostCreateRequest request)
    {
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .postCategory(postCategoryRepository.findById(request.getCategoryId())
                        .orElseThrow(() -> new IllegalArgumentException("카테고리 없음")))
                .isPublic(request.getIsPublic())// 나중에 로그인 연동 시 실제 User 엔티티
                .build();

        return postRepository.save(post);
    }
    // 게시글 수정
    @Transactional
    public Post updatePost(Long postId,PostUpdateRequest request)
    {
        Post existing = findById(postId);//기존 글
        PostCategory postCategory = postCategoryRepository.findById(request.getPostCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리 없음"));

        existing.updatePost(postCategory,request.getTitle(),request.getContent(),request.getIsPublic());
        return existing;
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // 조회수 증가
    @Transactional
    public void increaseViewCount(Long postId) {
        postRepository.increaseViewCount(postId);
    }

    // 좋아요 증가
    @Transactional
    public void increaseLikeCount(Long postId) {
        postRepository.increaseLikeCount(postId);
    }

    // 전체 게시글 조회
    @Transactional(readOnly = true)
    public List<Post> findAllOrderBy(String sort) {
        switch (sort) {
            case "newest":
                return postRepository.findAllByOrderByPostCreatedAtDesc();
            case "oldest":
                return postRepository.findAllByOrderByPostCreatedAtAsc();
            case "view_desc":
                return postRepository.findAllByOrderByViewCountDesc();
            case "like_desc":
                return postRepository.findAllByOrderByLikeCountDesc();
            default:
                return postRepository.findAllByOrderByPostCreatedAtDesc();
        }
    }

    // 카테고리별 게시글 조회
    @Transactional(readOnly = true)
    public List<Post> findByCategoryOrderBy(Long categoryId, String sort) {
        switch (sort) {
            case "newest":
                return postRepository.findByPostCategoryPostCategoryIdOrderByPostCreatedAtDesc(categoryId);
            case "oldest":
                return postRepository.findByPostCategoryPostCategoryIdOrderByPostCreatedAtAsc(categoryId);
            case "view_desc":
                return postRepository.findByPostCategoryPostCategoryIdOrderByViewCountDesc(categoryId);
            case "like_desc":
                return postRepository.findByPostCategoryPostCategoryIdOrderByLikeCountDesc(categoryId);
            default:
                return postRepository.findByPostCategoryPostCategoryIdOrderByPostCreatedAtDesc(categoryId);
        }
    }



}
