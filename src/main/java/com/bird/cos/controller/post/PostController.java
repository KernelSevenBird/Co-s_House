package com.bird.cos.controller.post;

import com.bird.cos.domain.post.Post;
import com.bird.cos.domain.post.PostCategory;
import com.bird.cos.dto.post.*;
import com.bird.cos.repository.post.PostCategoryRepository;
import org.springframework.ui.Model;
import com.bird.cos.service.post.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor

@RequestMapping("/post")
public class PostController
{
    private final PostService postService;
    private final PostCategoryRepository categoryRepository;
    private PostService postRepository;

    @GetMapping("/")
    public String showPostsList(Model model)
    {
        List<Post> postsList = postService.findAll();
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList", dtoList);
        return "list";
    }//게시글 목록 조회

    @GetMapping("/{postId}")
    public String showPostDetail(Model model, @PathVariable("postId") Long postId)
    {
        Post post = postService.findById(postId);
        PostResponse dto = PostResponse.from(post);
        model.addAttribute("post", dto);
        return "detail";

    }//게시글 상세 조회

    @GetMapping("/category/{categoryId}")
    public String showPostByCategory(Model model, @PathVariable("categoryId") Long categoryId)
    {
        List<Post> postsList = postService.findAllByCategory(categoryId);
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList", dtoList);
        return "list";
    }//게시글(카테고리) 조회

    /* 정렬 */
    @GetMapping("/view_desc")
    public String showPostOrderByViewCountDesc(Model model)
    {
        List<Post> postsList = postService.findAllOrderBy("view_desc");
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList", dtoList);
        return "list";
    }//조회수 - 내림차순

    @GetMapping("/like_desc")
    public String showPostOrderByLikeCountDesc(Model model)
    {
        List<Post> postsList = postService.findAllOrderBy("like_desc");
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList", dtoList);
        return "list";
    }//좋아요 - 내림차순

    @GetMapping("/newest")
    public String showPostOrderByNewest(Model model)
    {
        List<Post> postsList = postService.findAllOrderBy("newest");
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList", dtoList);
        return "list";
    }//최신순 정렬

    @GetMapping("/oldest")
    public String showPostOrderByOldest(Model model)
    {
        List<Post> postsList = postService.findAllOrderBy("oldest");
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList", dtoList);
        return "list";
    }//오래된순 정렬

    @GetMapping("category/{categoryId}/view_desc")
    public String showPostByCategoryOrderByViewCountDesc(Model model, @PathVariable("categoryId") Long categoryId)
    {
        List<Post> postsList = postService.findByCategoryOrderBy(categoryId,"view_desc");
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList", dtoList);
        return "list";
    }//게시글(카테고리) 조회수 - 내림차순

    @GetMapping("category/{categoryId}/like_desc")
    public String showPostByCategoryOrderByLikeCountDesc(Model model, @PathVariable("categoryId") Long categoryId)
    {
        List<Post> postsList = postService.findByCategoryOrderBy(categoryId,"like_desc");
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList", dtoList);
        return "list";
    }//게시글(카테고리) 좋아요 - 내림차순

    @GetMapping("/category/{categoryId}/newest")
    public String showPostByCategoryOrderByNewest(Model model, @PathVariable("categoryId") Long categoryId)
    {   List<Post> postsList = postService.findByCategoryOrderBy(categoryId,"newest");
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList",dtoList);
        return "list";
    }//최신순 정렬

    @GetMapping("/category/{categoryId}/oldest")
    public String showPostByCategoryOrderByOldest(Model model, @PathVariable("categoryId") Long categoryId)
    {
        List<Post> postsList = postService.findByCategoryOrderBy(categoryId,"oldest");
        List<PostListResponse> dtoList = postsList.stream()
                .map(PostListResponse::from) // post -> dto 변환
                .toList();
        model.addAttribute("postsList",dtoList);
        return "list";
    }//오래된순 정렬
    /*정렬*/

    @GetMapping("/save")
    public String saveForm(Model model)
    {
        List<PostCategory> categories = categoryRepository.findAll();
        List<PostCategoryResponse> dtoList = categories.stream()
                .map(PostCategoryResponse::from) // category -> dto 변환
                .toList();
        model.addAttribute("categories",categoryRepository.findAll());//카테고리 목록
        return "form";
    }//게시글 작성(입력)

    @PostMapping("/save")
    public String savePost(PostCreateRequest request)
    {
        // Service를 통해 저장
        postService.save(request);
        return "redirect:/post/"; // 저장 후 목록 페이지로
    }//게시글 작성(확인)

    @GetMapping("/update/{postId}")
    public String updateForm(Model model, @PathVariable("postId") Long postId)
    {
        Post post = postService.findById(postId);
        PostResponse dto = PostResponse.from(post);

        model.addAttribute("post",dto);
        model.addAttribute("categories",categoryRepository.findAll());
        return "form";
    }//수정(입력)

    @PostMapping("/update")
    public String updatePost(PostUpdateRequest postUpdateRequest, RedirectAttributes redirectAttributes)
    {
        try {
            postService.updatePost(postUpdateRequest.getPostCategoryId(),postUpdateRequest);
            redirectAttributes.addFlashAttribute("message", "게시글이 수정되었습니다.");

            return "redirect:/post/update/" + postUpdateRequest.getPostCategoryId();
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/post/update/" + postUpdateRequest.getPostCategoryId();
        }
    }//수정()

    @PostMapping("/delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId,RedirectAttributes redirectAttributes)
    {
        try {
            postService.deletePost(postId);
            redirectAttributes.addFlashAttribute("message", "게시글이 삭제되었습니다.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/"; // 보통 목록 페이지로 리다이렉트
    }

}
