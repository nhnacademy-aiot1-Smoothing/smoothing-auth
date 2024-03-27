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
        if (!refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)) {
            throw new RuntimeException("일치하는 값이 없습니다.");
        }

        refreshTokenRepository.deleteByUserIdAndRefreshToken(userId, refreshToken);
    }
}
