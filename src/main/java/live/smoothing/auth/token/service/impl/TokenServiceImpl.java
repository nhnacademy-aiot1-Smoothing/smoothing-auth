package live.smoothing.auth.token.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import live.smoothing.auth.token.entity.RefreshToken;
import live.smoothing.auth.token.properties.JwtProperties;
import live.smoothing.auth.token.dto.LoginTokenResponse;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.exception.RefreshTokenNotExist;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.service.TokenService;
import live.smoothing.auth.token.util.JwtTokenUtil;
import live.smoothing.auth.token.util.JwtUtil;
import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사용자의 로그인, 로그아웃, 토큰 재발급 기능을 제공하는 서비스 클래스의 구현체
 *
 * @author 김지윤, 우혜승, 하지현
 */
@Slf4j
@RequiredArgsConstructor
@Service("refreshTokenService")
public class TokenServiceImpl implements TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;
    private final UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public LoginTokenResponse issue(User user) {

        String accessToken = JwtTokenUtil.createToken(user.getUserId(), user.getRoles(),
                jwtProperties.getAccessTokenExpirationTime());

        String refreshToken = JwtTokenUtil.createToken(user.getUserId(),
                user.getRoles(), jwtProperties.getRefreshTokenExpirationTime());

        refreshTokenRepository.save(new RefreshToken(user.getUserId(), refreshToken));
        deleteExpiredToken(user.getUserId());

        return new LoginTokenResponse(accessToken, refreshToken, jwtProperties.getTokenPrefix());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(String userId, String refreshToken) {

        if (!refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)) {
            throw new RefreshTokenNotExist();
        }

        refreshTokenRepository.deleteByUserIdAndRefreshToken(userId, refreshToken);
        deleteExpiredToken(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReissueResponse reissue(String userId, String refreshToken) {

        if (!refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)) {
            throw new RefreshTokenNotExist();
        }

        User user = userService.getUser(userId);

        ReissueResponse response = new ReissueResponse();
        response.setAccessToken(JwtTokenUtil.createToken(userId, user.getRoles(), jwtProperties.getAccessTokenExpirationTime()));
        response.setTokenType("Bearer");

        return response;
    }

    @Override
    public void deleteExpiredToken(String userId) {
        List<String> tokens = refreshTokenRepository.findAllByUserId(userId);
        for (String token : tokens) {
            try {
                if (JwtUtil.requireReissue(token)) {
                    refreshTokenRepository.deleteByUserIdAndRefreshToken(userId, token);
                }
            } catch (JsonProcessingException e) {
                log.error("토큰 파싱 중 오류 발생", e);
            }

        }
    }

}
