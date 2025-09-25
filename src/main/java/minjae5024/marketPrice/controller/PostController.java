package minjae5024.marketPrice.controller;

import minjae5024.marketPrice.dto.PostDto;
import minjae5024.marketPrice.service.UserDetailsImpl;
import minjae5024.marketPrice.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public Page<PostDto.ListResponse> searchPosts(
            @RequestParam(required = false) Long marketId,
            @RequestParam(required = false, defaultValue = "title") String searchType,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.searchPosts(marketId, searchType, keyword, pageable, userDetails);
    }

    @GetMapping("/{postId}")
    public PostDto.DetailResponse getPostById(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getPostById(postId, userDetails);
    }

    @PostMapping
    public ResponseEntity<String> createPost(
            @Valid @RequestBody PostDto.Request requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(requestDto, userDetails);
        return ResponseEntity.ok("게시글이 등록되었습니다.");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails);
        return ResponseEntity.ok("게시글이 삭제되었습니다.");
    }
}
