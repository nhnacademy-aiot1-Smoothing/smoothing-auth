package live.smoothing.auth.token.repository;

public interface TempRefreshTokenRepository {

    boolean lock(String refreshToken);
}
