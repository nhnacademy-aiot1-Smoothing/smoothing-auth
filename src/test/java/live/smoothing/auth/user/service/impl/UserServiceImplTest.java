package live.smoothing.auth.user.service.impl;

import live.smoothing.auth.user.adapter.UserAdapter;
import live.smoothing.auth.user.domain.User;
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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
        Mockito.when(passwordEncoder.encode(loginRequest.getUserPassword())).thenReturn("1234");

        assertDoesNotThrow(() -> userService.login(loginRequest, user));
    }

    @Test
    void login_inValidPassword() {

        user.setUserPassword("123");
        Mockito.when(passwordEncoder.encode(loginRequest.getUserPassword())).thenReturn("1234");

        assertThrows(PasswordNotValid.class, () -> userService.login(loginRequest, user));
    }

    @Test
    void getUser_serverError() {

        Mockito.when(userAdapter.getSimpleUser(userId)).thenThrow(UserServerError.class);

        assertThrows(UserServerError.class, ()->userService.getUser(userId));
    }

    @Test
    void getUser_notFound() {

//        Mockito.when(userAdapter.getSimpleUser(userId)).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFound.class, ()->userService.getUser(userId));
    }

    @Test
    void getUser_exist() {

//        SimpleUserResponse userResponse = new SimpleUserResponse();
//        userResponse.setUser(new SimpleUserResponse.InnerUser());
//        Mockito.when(userAdapter.getSimpleUser(userId)).thenReturn(Optional.of(userResponse));
//
//        assertNotNull(userService.getUser(userId));
    }
}