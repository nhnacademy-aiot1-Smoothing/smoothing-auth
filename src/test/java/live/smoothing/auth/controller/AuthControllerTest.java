package live.smoothing.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.entity.RefreshToken;
import live.smoothing.auth.token.service.RefreshTokenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @MockBean
    private RefreshTokenService refreshTokenService;

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

        Mockito.when(refreshTokenService.reissue(eq(userId), eq(refreshToken.getRefreshToken()))).thenReturn(reissueResponse);

        mockMvc.perform(post("/api/auth/refresh").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(refreshToken)).header("X-USER-ID", userId)).andExpect(status().isOk()).andDo(print()).andExpect(content().bytes(objectMapper.writeValueAsBytes(reissueResponse)));
    }

    @Test
    void logout() throws Exception {

    }
}