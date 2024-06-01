package live.smoothing.auth.token.repository.impl;

import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.entity.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public List<String> findAllByUserId(String userId) {

        List<String> result;

        Set<String> members = redisTemplate.opsForSet().members(userId);
        if (Objects.isNull(members) || members.isEmpty()) {
            result = new ArrayList<>();
        } else {
            result = new ArrayList<>(members);
        }
        return result;
    }

}
