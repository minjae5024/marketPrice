package minjae5024.marketPrice.service;

import minjae5024.marketPrice.dto.LoginRequestDto;
import minjae5024.marketPrice.dto.RegisterRequestDto;
import minjae5024.marketPrice.entity.User;
import minjae5024.marketPrice.jwt.JwtUtil;
import minjae5024.marketPrice.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthService authService;

    @Test
    @DisplayName("회원가입 성공")
    void registerSuccess() {
        // Given
        RegisterRequestDto requestDto = new RegisterRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setPassword("password");
        requestDto.setEmail("test@test.com");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // When
        authService.register(requestDto);

        // Then
        verify(userRepository).save(any(User.class));
    }

    @Test
    @DisplayName("회원가입 실패 - 중복된 아이디")
    void registerFailUsernameIsDuplicated() {
        // Given
        RegisterRequestDto requestDto = new RegisterRequestDto();
        requestDto.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(new User()));

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            authService.register(requestDto);
        });
    }

    @Test
    @DisplayName("로그인 성공")
    void loginSuccess() {
        // Given
        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setPassword("password");

        User user = new User("testuser", "encodedPassword", "test@test.com");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(jwtUtil.createToken("testuser")).thenReturn("test-token");

        // When
        String token = authService.login(requestDto);

        // Then
        assertThat(token).isEqualTo("test-token");
    }

    @Test
    @DisplayName("로그인 실패 - 존재하지 않는 사용자")
    void loginFailUserNotFound() {
        // Given
        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("nouser");

        when(userRepository.findByUsername("nouser")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            authService.login(requestDto);
        });
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    void loginFailPasswordIsWrong() {
        // Given
        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername("testuser");
        requestDto.setPassword("wrongpassword");

        User user = new User("testuser", "encodedPassword", "test@test.com");
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongpassword", "encodedPassword")).thenReturn(false);

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            authService.login(requestDto);
        });
    }
}