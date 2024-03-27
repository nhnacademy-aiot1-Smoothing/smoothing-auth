package live.smoothing.auth.token.service.impl;

import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.service.RefreshTokenService;
import live.smoothing.auth.token.util.JwtTokenUtil;
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

    @Override
    public ReissueResponse reissue(String userId, String refreshToken) {

        if(!refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)) {
            throw new RuntimeException();
        }

        ReissueResponse response = new ReissueResponse();
        response.setAccessToken(JwtTokenUtil.createToken(userId, null, 1));
        response.setTokenType("Bearer");

        return response;
    }
}
