package live.smoothing.auth.token.repository.impl;

import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * redis에서 refreshToken을 관리하는 클래스의 구현체
 *
 * @author 김지윤, 우혜승, 하지현
 */
@RequiredArgsConstructor
@Repository("tokenRepository")
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(RefreshToken refreshToken) {

        redisTemplate.opsForSet().add(refreshToken.getUserId(), refreshToken.getRefreshToken());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existByUserIdAndRefreshToken(String userId, String refreshToken) {

        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(userId, refreshToken));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByUserIdAndRefreshToken(String userId, String refreshToken) {

        redisTemplate.opsForSet().remove(userId, refreshToken);
    }

}
