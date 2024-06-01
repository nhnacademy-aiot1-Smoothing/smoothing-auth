package live.smoothing.auth.user.service.impl;

import feign.FeignException;
import live.smoothing.auth.user.adapter.UserAdapter;
import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.dto.InnerUserDto;
import live.smoothing.auth.user.dto.LoginRequest;
import live.smoothing.auth.user.dto.SimpleUserResponse;
import live.smoothing.auth.user.exeption.PasswordNotValid;
import live.smoothing.auth.user.exeption.UserNotFound;
import live.smoothing.auth.user.exeption.UserServerError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserAdapter userAdapter;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private final String userId = "test";
    private final String userPassword = "1234";
    private final User user = new User();

    private LoginRequest loginRequest;

    @BeforeEach
    void init() {

        loginRequest = new LoginRequest();
        loginRequest.setUserId(userId);
        loginRequest.setUserPassword(userPassword);
    }

    @Test
    void login_validPassword() {
        user.setUserPassword("1234");

        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);

        assertDoesNotThrow(() -> userService.login(loginRequest, user));
    }

    @Test
    void login_inValidPassword() {
        user.setUserPassword("123");

        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(PasswordNotValid.class, () -> userService.login(loginRequest, user));
    }

    @Test
    void getUser_serverError() {
        FeignException feignException = Mockito.mock(FeignException.class);
        when(feignException.status()).thenReturn(500);
        when(userAdapter.getSimpleUser(userId)).thenThrow(feignException);

        assertThrows(UserServerError.class, ()->userService.getUser(userId));
    }

    @Test
    void getUser_notFound() {

        FeignException feignException = Mockito.mock(FeignException.class);
        when(feignException.status()).thenReturn(404);
        when(userAdapter.getSimpleUser(userId)).thenThrow(feignException);

        assertThrows(UserNotFound.class, ()->userService.getUser(userId));
    }

    @Test
    void getUser() {

        SimpleUserResponse simpleUserResponse = new SimpleUserResponse();
        InnerUserDto innerUser = new InnerUserDto();
        List<String> roles = List.of("ROLE_TEST");
        ReflectionTestUtils.setField(innerUser, "userId", userId);
        ReflectionTestUtils.setField(innerUser, "userPassword", userPassword);
        ReflectionTestUtils.setField(simpleUserResponse, "user", innerUser);
        ReflectionTestUtils.setField(simpleUserResponse, "roles", roles);

        ResponseEntity<SimpleUserResponse> response = new ResponseEntity<>(simpleUserResponse, null, 200);

        when(userAdapter.getSimpleUser(userId)).thenReturn(response);

        assertEquals(userId, userService.getUser(userId).getUserId());
    }
}