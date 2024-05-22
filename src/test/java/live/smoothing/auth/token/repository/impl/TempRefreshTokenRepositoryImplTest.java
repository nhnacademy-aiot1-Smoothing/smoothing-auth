package live.smoothing.auth.token.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class TempRefreshTokenRepositoryImplTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    private TempRefreshTokenRepositoryImpl tempRefreshTokenRepository;

    @BeforeEach
    void setUp() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @Test
    void lock_notExist_returnTrue() {

        assertTrue(tempRefreshTokenRepository.lock("test"));
    }

    @Test
    void lock_exist_returnFalse() {
        tempRefreshTokenRepository.lock("test");

        assertFalse(tempRefreshTokenRepository.lock("test"));
    }
}