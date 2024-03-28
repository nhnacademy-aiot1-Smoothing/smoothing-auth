package live.smoothing.auth.token.service.impl;

import live.smoothing.auth.properties.JwtProperties;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.util.JwtTokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RefreshTokenServiceImplTest {

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private RefreshTokenServiceImpl refreshTokenService;

    private final String userId = "test";

    private final String refreshToken = "test12";

    @BeforeAll
    static void beforeAll() {

        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("thisistestkeythisissecretkeythisisfortestbeforeall");
        new JwtTokenUtil().setSecret(jwtProperties);
    }

    @Test
    void reissue_existByUserIdAndRefreshToken() {

        Mockito.when(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)).thenReturn(true);

        ReissueResponse response = refreshTokenService.reissue(userId, refreshToken);
        assertEquals("Bearer", response.getTokenType());
        response.getAccessToken();
    }

    @Test
    void reissue_notExistByUserIdAndRefreshToken() {

        Mockito.when(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> {
            refreshTokenService.reissue(userId, refreshToken);
        });
    }

}