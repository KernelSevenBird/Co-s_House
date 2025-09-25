package com.bird.cos.service.post;

import com.bird.cos.domain.post.PostCategory;
import com.bird.cos.dto.post.PostCategoryRequest;
import com.bird.cos.repository.post.PostCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCategoryService
{
    private final PostCategoryRepository postCategoryRepository;

    // 단일 카테고리
    @Transactional(readOnly = true)
    public PostCategory findById(Long postCategoryId)
    {
        return postCategoryRepository.findById(postCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리는 존재하지 않습니다."));
    }

    //모든 카테고리
    @Transactional(readOnly = true)
    public List<PostCategory> findAll()
    {
        return postCategoryRepository.findAll();
    }

    // 카테고리 저장
    @Transactional
    public PostCategory save(PostCategoryRequest request)
    {
        PostCategory postCategory = PostCategory.builder()
                .postCategoryName(request.getPostCategoryName())
                .postCategoryType(request.getPostCategoryType())
                .iconUrl(request.getIconUrl())
                .displayOrder((request.getDisplayOrder()))
                .build();
        return postCategoryRepository.save(postCategory);
    }

    // 카테고리 수정
    @Transactional
    public PostCategory updatePostCategory(Long postCategoryId,PostCategoryRequest request) {
        PostCategory existing = findById(postCategoryId);
        existing.updatePostCategory(existing.getPostCategoryName(),existing.getPostCategoryType(),existing.getIconUrl(),existing.getDisplayOrder());
        return existing;
    }

    //카테고리 삭제
    @Transactional
    public void deletePost(Long postCategoryId)
    {
        postCategoryRepository.deleteById(postCategoryId);
    }

}
