package live.smoothing.auth.token.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.auth.properties.JwtProperties;
import live.smoothing.auth.token.dto.LoginTokenResponse;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.util.JwtTokenUtil;
import live.smoothing.auth.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {
    @Mock
    private JwtProperties jwtProperties;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @InjectMocks
    private TokenServiceImpl tokenService;

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

        ReissueResponse response = tokenService.reissue(userId, refreshToken);

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
            tokenService.reissue(userId, refreshToken);
        });
    }

    @Test
    void delete_existByUserIdAndRefreshToken() {

        when(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)).thenReturn(true);

        tokenService.delete(userId, refreshToken);

        verify(refreshTokenRepository).deleteByUserIdAndRefreshToken(userId, refreshToken);

    }

    @Test
    void delete_notExistByUserIdAndRefreshToken() {

        when(refreshTokenRepository.existByUserIdAndRefreshToken(userId, refreshToken)).thenReturn(false);

        Assertions.assertThrows(RuntimeException.class, () -> {
            tokenService.delete(userId, refreshToken);
        });

        verify(refreshTokenRepository, never()).deleteByUserIdAndRefreshToken(userId, refreshToken);
    }

    @Test
    void issue() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        User input = new User("test","1234", List.of("test"));

        Mockito.when(jwtProperties.getAccessTokenExpirationTime()).thenReturn(100);
        Mockito.when(jwtProperties.getRefreshTokenExpirationTime()).thenReturn(200);
        LoginTokenResponse output = tokenService.issue(input);
        JsonNode jsonNode = objectMapper.readTree(Base64.getDecoder().decode(output.getAccessToken().split("\\.")[1]));

        assertEquals(input.getUserId(),jsonNode.get("userId").asText());
    }
}