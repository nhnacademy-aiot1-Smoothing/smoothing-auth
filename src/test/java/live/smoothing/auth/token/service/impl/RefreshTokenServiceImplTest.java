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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        when(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)).thenReturn(true);

        ReissueResponse response = refreshTokenService.reissue(userId, refreshToken);

        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = response.getAccessToken().split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        int userIdIndex = payload.indexOf("\"userId\"");
        int colonIndex = payload.indexOf(":", userIdIndex);
        int commaIndex = payload.indexOf(",", colonIndex);
        int endIndex = payload.indexOf("}", colonIndex);
        int endOfValueIndex = (commaIndex != -1 && commaIndex < endIndex) ? commaIndex : endIndex;
        String result = payload.substring(colonIndex + 2, endOfValueIndex - 1);

        assertEquals(userId, result);
        assertEquals("Bearer", response.getTokenType());
    }

    @Test
    void reissue_notExistByUserIdAndRefreshToken() {

        when(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> {
            refreshTokenService.reissue(userId, refreshToken);
        });
    }

    @Test
    void delete_existByUserIdAndRefreshToken() {

        when(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)).thenReturn(true);

        refreshTokenService.delete(userId, refreshToken);

        verify(refreshTokenRepository).deleteByUserIdAndRefreshToken(userId, refreshToken);

    }

    @Test
    void delete_notExistByUserIdAndRefreshToken() {

        when(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> {
            refreshTokenService.delete(userId, refreshToken);
        });

        verify(refreshTokenRepository, never()).deleteByUserIdAndRefreshToken(userId, refreshToken);
    }

}