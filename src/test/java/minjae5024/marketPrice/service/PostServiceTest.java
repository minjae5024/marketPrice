package minjae5024.marketPrice.service;

import minjae5024.marketPrice.dto.PostDto;
import minjae5024.marketPrice.entity.Market;
import minjae5024.marketPrice.entity.Post;
import minjae5024.marketPrice.entity.User;
import minjae5024.marketPrice.repository.MarketRepository;
import minjae5024.marketPrice.repository.PostRepository;
import minjae5024.marketPrice.repository.UserRepository;
import minjae5024.marketPrice.security.UserDetailsImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MarketRepository marketRepository;

    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("게시글 생성 성공")
    void createPostSuccess() {
        // Given
        PostDto.Request requestDto = new PostDto.Request();
        requestDto.setMarketId(1L);
        requestDto.setTitle("TestTitle");
        requestDto.setContent("TestContent");

        User user = new User("testuser", "password", "test@test.com");
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        Market market = new Market("marketCode", "TestMarket", 0.0, 0.0);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(marketRepository.findById(1L)).thenReturn(Optional.of(market));

        // When
        postService.createPost(requestDto, userDetails);

        // Then
        verify(postRepository).save(any(Post.class));
    }

    @Test
    @DisplayName("게시글 조회 성공")
    void getPostByIdSuccess() {
        // Given
        Long postId = 1L;
        User user = new User("testuser", "password", "test@test.com");
        Market market = new Market("marketCode", "TestMarket", 0.0, 0.0);
        Post post = new Post("TestTitle", "TestContent", user, market);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // When
        PostDto.DetailResponse response = postService.getPostById(postId, userDetails);

        // Then
        assertThat(response.getTitle()).isEqualTo("TestTitle");
        assertThat(response.isOwner()).isTrue();
        assertThat(post.getViewCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 삭제 성공")
    void deletePostSuccess() {
        // Given
        Long postId = 1L;
        User user = new User("testuser", "password", "test@test.com");
        Market market = new Market("marketCode", "TestMarket", 0.0, 0.0);
        Post post = new Post("TestTitle", "TestContent", user, market);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // When
        postService.deletePost(postId, userDetails);

        // Then
        verify(postRepository).delete(post);
    }

    @Test
    @DisplayName("게시글 삭제 실패 - 권한 없음")
    void deletePostFailWhenNotOwner() {
        // Given
        Long postId = 1L;
        User owner = new User("owner", "password", "owner@test.com");
        User otherUser = new User("other", "password", "other@test.com");
        Market market = new Market("marketCode", "TestMarket", 0.0, 0.0);
        Post post = new Post("TestTitle", "TestContent", owner, market);
        UserDetailsImpl userDetails = new UserDetailsImpl(otherUser);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // When & Then
        assertThrows(AccessDeniedException.class, () -> {
            postService.deletePost(postId, userDetails);
        });
    }
}
