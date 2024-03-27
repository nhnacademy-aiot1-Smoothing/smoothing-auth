package live.smoothing.auth.token.service;

public interface RefreshTokenService {
    boolean existRefreshToken(String userId, String refreshToken);
    void save(String userId, String refreshToken);
    void delete(String userId, String refreshToken);
}
