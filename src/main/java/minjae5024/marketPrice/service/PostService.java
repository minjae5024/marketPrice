package minjae5024.marketPrice.service;

import minjae5024.marketPrice.dto.PostDto;
import minjae5024.marketPrice.entity.Market;
import minjae5024.marketPrice.entity.Post;
import minjae5024.marketPrice.entity.User;
import minjae5024.marketPrice.repository.MarketRepository;
import minjae5024.marketPrice.repository.PostRepository;
import minjae5024.marketPrice.repository.UserRepository;
import minjae5024.marketPrice.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MarketRepository marketRepository;

    @Transactional(readOnly = true)
    public Page<PostDto.ListResponse> searchPosts(Long marketId, String searchType, String keyword, Pageable pageable, UserDetailsImpl userDetails) {
        Page<Post> posts = postRepository.search(marketId, searchType, keyword, pageable);

        return posts.map(post -> {
            boolean isOwner = userDetails != null && post.getUser().getUsername().equals(userDetails.getUsername());
            return new PostDto.ListResponse(post, isOwner);
        });
    }

    @Transactional
    public PostDto.DetailResponse getPostById(Long postId, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        post.increaseViewCount(); 

        boolean isOwner = userDetails != null && post.getUser().getUsername().equals(userDetails.getUsername());
        return new PostDto.DetailResponse(post, isOwner);
    }

    @Transactional
    public void createPost(PostDto.Request requestDto, UserDetailsImpl userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
        );
        Market market = marketRepository.findById(requestDto.getMarketId()).orElseThrow(
                () -> new IllegalArgumentException("시장을 찾을 수 없습니다.")
        );
        Post post = new Post(requestDto.getTitle(), requestDto.getContent(), user, market);
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId, UserDetailsImpl userDetails) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );

        if (!post.getUser().getUsername().equals(userDetails.getUsername())) {
            throw new AccessDeniedException("게시글을 삭제할 권한이 없습니다.");
        }

        postRepository.delete(post);
    }
}
