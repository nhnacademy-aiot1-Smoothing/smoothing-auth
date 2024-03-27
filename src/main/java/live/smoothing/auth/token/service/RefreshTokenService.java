package live.smoothing.auth.token.service;

public interface RefreshTokenService {

    void delete(String userId, String refreshToken);
}
