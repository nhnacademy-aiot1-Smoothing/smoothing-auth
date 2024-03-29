package live.smoothing.auth.token.service.impl;

import live.smoothing.auth.properties.JwtProperties;
import live.smoothing.auth.token.dto.LoginTokenResponse;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.exeption.RefreshTokenNotExist;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.service.TokenService;
import live.smoothing.auth.token.util.JwtTokenUtil;
import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("refreshTokenService")
public class TokenServiceImpl implements TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;
    private final UserService userService;

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
            throw new RefreshTokenNotExist();
        }

        refreshTokenRepository.deleteByUserIdAndRefreshToken(userId, refreshToken);
    }

    @Override
    public ReissueResponse reissue(String userId, String refreshToken) {

        if (!refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)) {
            throw new RefreshTokenNotExist();
        }

        User user = userService.getUser(userId);

        ReissueResponse response = new ReissueResponse();
        response.setAccessToken(JwtTokenUtil.createToken(userId, user.getUserAuth(), jwtProperties.getAccessTokenExpirationTime()));
        response.setTokenType("Bearer");

        return response;
    }
}
