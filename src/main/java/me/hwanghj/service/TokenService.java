package me.hwanghj.service;

import lombok.RequiredArgsConstructor;
import me.hwanghj.config.jwt.TokenProvider;
import me.hwanghj.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor            // final 필드 생성자 자동 생성.
@Service
public class TokenService {
    // 전달받은 리프레시 토큰으로 토큰 유효성 검사 진행, 유효한 경우 리프레시 토큰으로 사용자 ID 찾고, 새로운 액세스 토큰을 생성.

    // 생성자 주입.
    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생.
        if (!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
