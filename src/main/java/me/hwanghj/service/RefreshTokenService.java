package me.hwanghj.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.hwanghj.config.jwt.TokenProvider;
import me.hwanghj.domain.RefreshToken;
import me.hwanghj.repository.RefreshTokenRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor            // final 필드 생성자 자동 생성.
@Service
public class RefreshTokenService {

    // 생성자 주입.
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository
                .findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
    }

    @Transactional
    public void delete() {
        String token = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getCredentials()
                .toString();
        Long userId = tokenProvider.getUserId(token);

        refreshTokenRepository.deleteByUserId(userId);
    }
}
