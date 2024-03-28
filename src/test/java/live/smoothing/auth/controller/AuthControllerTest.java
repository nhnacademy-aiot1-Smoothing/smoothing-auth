package live.smoothing.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.auth.password.dto.PasswordDto;
import live.smoothing.auth.password.service.PasswordEncodingService;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.entity.RefreshToken;
import live.smoothing.auth.token.service.RefreshTokenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @MockBean
    private RefreshTokenService refreshTokenService;

    @MockBean
    private PasswordEncodingService passwordEncodingService;

    @Autowired
    private MockMvc mockMvc;

    private final String userId = "test";

    private final RefreshToken refreshToken = new RefreshToken(userId, "sdflkasjdflksdajfklsdajflksdajfl");



    @Test
    void reissueToken() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        ReissueResponse reissueResponse = new ReissueResponse();
        reissueResponse.setAccessToken("asddfljsalfkjdslfjlasdkflasdkfl");
        reissueResponse.setTokenType("Bearer");

        when(refreshTokenService.reissue(eq(userId), eq(refreshToken.getRefreshToken()))).thenReturn(reissueResponse);

        mockMvc.perform(post("/api/auth/refresh").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(refreshToken)).header("X-USER-ID", userId)).andExpect(status().isOk()).andDo(print()).andExpect(content().bytes(objectMapper.writeValueAsBytes(reissueResponse)));
    }

    @Test
    void logout() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(delete("/api/auth//logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refreshToken))
                        .header("X-USER-ID", userId))
                .andExpect(status().isOk());

        verify(refreshTokenService).delete(userId, refreshToken.getRefreshToken());
    }

    @Test
    void encodePassword() throws Exception {

        PasswordDto originalPassword = new PasswordDto("originalPassword");
        PasswordDto encodedPassword = new PasswordDto("encodedPassword");

        when(passwordEncodingService.encodePassword(any(PasswordDto.class))).thenReturn(encodedPassword);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/auth/encode")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(originalPassword)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.password").value(encodedPassword.getPassword()));
    }
}