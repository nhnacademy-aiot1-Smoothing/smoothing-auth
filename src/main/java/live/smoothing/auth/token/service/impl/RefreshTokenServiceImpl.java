package live.smoothing.auth.token.service.impl;

import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("refreshTokenService")
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void delete(String userId, String refreshToken) {
        refreshTokenRepository.deleteByUserIdAndRefreshToken(userId, refreshToken);
    }
}
