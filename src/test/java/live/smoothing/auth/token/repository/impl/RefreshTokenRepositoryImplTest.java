package live.smoothing.auth.token.repository.impl;

import live.smoothing.auth.token.entity.RefreshToken;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RefreshTokenRepositoryImplTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private final String userId = "testUser";

    private final String refreshToken = "thisisrefreshtokenfortestcode";

    @Test
    void save() {

        refreshTokenRepository.save(new RefreshToken(userId, refreshToken));

        assertTrue(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken));
    }

    @Test
    void existByUserIdAndRefreshToken() {

        refreshTokenRepository.save(new RefreshToken(userId, refreshToken));

        assertTrue(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken));
    }

    @Test
    void deleteByUserIdAndRefreshToken() {

        refreshTokenRepository.save(new RefreshToken(userId, refreshToken));
        refreshTokenRepository.deleteByUserIdAndRefreshToken(userId, refreshToken);

        assertFalse(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken));
    }
}