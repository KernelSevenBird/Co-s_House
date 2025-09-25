package com.bird.cos.repository.post;

import com.bird.cos.domain.post.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryRepository extends JpaRepository<PostCategory,Long>
{


}//카테고리 repository
