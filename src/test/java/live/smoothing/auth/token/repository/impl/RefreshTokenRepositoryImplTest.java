package live.smoothing.auth.token.repository.impl;

import live.smoothing.auth.token.entity.RefreshToken;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RefreshTokenRepositoryImplTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    private final String userId = "testUser";

    private final String refreshToken = "thisisrefreshtokenfortestcode";

    @BeforeEach
    void setUp() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

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

    @Test
    void findAllByUserId_notExistMember_returnEmptyList() {

        assertTrue(refreshTokenRepository.findAllByUserId(userId).isEmpty());
    }

    @Test
    void findAllByUserId_existMember_returnList() {

        refreshTokenRepository.save(new RefreshToken(userId, refreshToken));

        assertFalse(refreshTokenRepository.findAllByUserId(userId).isEmpty());
    }
}