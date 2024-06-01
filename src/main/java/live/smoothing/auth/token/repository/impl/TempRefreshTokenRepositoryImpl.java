package live.smoothing.auth.token.repository.impl;

import live.smoothing.auth.token.repository.TempRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@RequiredArgsConstructor
@Repository("tempRefreshTokenRepository")
public class TempRefreshTokenRepositoryImpl implements TempRefreshTokenRepository {

    private final StringRedisTemplate redisTemplate;

    private final static Duration EXPIRE_TIME = Duration.ofSeconds(3);

    @Override
    public boolean lock(String refreshToken) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(refreshToken, "0", EXPIRE_TIME));
    }
}
