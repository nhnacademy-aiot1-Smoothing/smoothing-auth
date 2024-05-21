package live.smoothing.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.auth.password.service.PasswordEncodingService;
import live.smoothing.auth.token.service.TokenService;
import live.smoothing.auth.user.dto.LoginRequest;
import live.smoothing.auth.user.exeption.UserNotFound;
import live.smoothing.auth.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest({AuthController.class, ErrorAdvice.class})
@AutoConfigureMockMvc(addFilters = false)
class ErrorAdviceTest {

    @MockBean
    private UserService userService;

    @MockBean
    private TokenService tokenService;

    @MockBean
    private PasswordEncodingService passwordEncodingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void error() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        ReflectionTestUtils.setField(loginRequest, "userId", "test");
        when(userService.getUser(loginRequest.getUserId())).thenThrow(new UserNotFound());


        mockMvc.perform(post("/api/auth/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isNotFound());
    }
}