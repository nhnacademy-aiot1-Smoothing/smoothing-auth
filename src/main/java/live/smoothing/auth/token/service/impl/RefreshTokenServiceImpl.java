package live.smoothing.auth.token.service.impl;

import live.smoothing.auth.token.entity.RefreshToken;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("refreshTokenService")
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public boolean existRefreshToken(String userId, String refreshToken) {
        return refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken);
    }

    @Override
    public void save(String userId, String refreshToken) {
        refreshTokenRepository.save(new RefreshToken(userId, refreshToken));
    }

    @Override
    public void delete(String userId, String refreshToken) {
        refreshTokenRepository.deleteByUserIdAndRefreshToken(userId, refreshToken);
    }
}
