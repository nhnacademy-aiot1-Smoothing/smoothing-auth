package live.smoothing.auth.token.repository;

import live.smoothing.auth.token.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository {

    void save(RefreshToken refreshToken);

    boolean existByUserIdAndRefreshToken(String userId, String refreshToken);

    void deleteByUserIdAndRefreshToken(String userId, String refreshToken);
}
