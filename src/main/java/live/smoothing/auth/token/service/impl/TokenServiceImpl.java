package live.smoothing.auth.token.service.impl;

import live.smoothing.auth.properties.JwtProperties;
import live.smoothing.auth.token.dto.LoginTokenResponse;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.service.TokenService;
import live.smoothing.auth.token.util.JwtTokenUtil;
import live.smoothing.auth.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("refreshTokenService")
public class TokenServiceImpl implements TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    @Override
    public LoginTokenResponse issue(User user) {

        String accessToken = JwtTokenUtil.createToken(user.getUserId(), user.getUserAuth(),
                jwtProperties.getAccessTokenExpirationTime());

        String refreshToken = JwtTokenUtil.createToken(user.getUserId(),
                user.getUserAuth(), jwtProperties.getRefreshTokenExpirationTime());

        return new LoginTokenResponse(accessToken, refreshToken, jwtProperties.getTokenPrefix());
    }

    @Override
    public void delete(String userId, String refreshToken) {

        if (!refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)) {
            throw new RuntimeException("일치하는 값이 없습니다.");
        }

        refreshTokenRepository.deleteByUserIdAndRefreshToken(userId, refreshToken);
    }

    @Override
    public ReissueResponse reissue(String userId, String refreshToken) {

        if (!refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)) {
            throw new RuntimeException();
        }

        ReissueResponse response = new ReissueResponse();
        response.setAccessToken(JwtTokenUtil.createToken(userId, null, 1));
        response.setTokenType("Bearer");

        return response;
    }
}
