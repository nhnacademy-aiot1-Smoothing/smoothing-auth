//package live.smoothing.auth.user.service.impl;
//
//import live.smoothing.auth.user.adapter.UserAdapter;
//import live.smoothing.auth.user.dto.LoginRequest;
//import live.smoothing.auth.user.dto.SimpleUserResponse;
//import live.smoothing.auth.user.exeption.LoginFailException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.eq;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceImplTest {
//
//    @Mock
//    private UserAdapter userAdapter;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    private final String userId = "test";
//
//    private LoginRequest loginRequest;
//
//    @BeforeEach
//    void init() {
//        loginRequest = new LoginRequest();
//        loginRequest.setUserId(userId);
//    }
//
//    @Test
//    void login_userServerError_throwServerError() {
//
//        Mockito.when(userAdapter.getSimpleUser(userId)).thenThrow(RuntimeException.class);
//
//        assertThrows(LoginFailException.class, () -> userService.login(loginRequest));
//    }
//
//    @Test
//    void login_userNotFound_throwUserNotFound() {
//
//        Mockito.when(userAdapter.getSimpleUser(userId)).thenReturn(Optional.empty());
//
//        assertThrows(LoginFailException.class, () -> userService.login(loginRequest));
//    }
//
//    @Test
//    void login_notEqualPassword_throwPasswordError() {
//
//        String userPassword = "1234";
//        SimpleUserResponse simpleUserResponse = new SimpleUserResponse();
//        simpleUserResponse.setUserPassword(userPassword);
//        loginRequest.setUserPassword("1235");
//        Mockito.when(userAdapter.getSimpleUser(userId)).thenReturn(Optional.of(simpleUserResponse));
//        Mockito.when(passwordEncoder.encode(eq(loginRequest.getUserPassword()))).thenReturn(loginRequest.getUserPassword());
//
//        assertThrows(LoginFailException.class, () -> userService.login(loginRequest));
//    }
//
////    @Test
////    void login_equalPassword_returnUser() {
////
////        String userPassword = "1234";
////        SimpleUserResponse simpleUserResponse = new SimpleUserResponse();
////        simpleUserResponse.setUserPassword(userPassword);
////        simpleUserResponse.setUserId(userId);
////        loginRequest.setUserPassword("1234");
////        Mockito.when(userAdapter.getSimpleUser(userId)).thenReturn(Optional.of(simpleUserResponse));
////        Mockito.when(passwordEncoder.encode(eq(loginRequest.getUserPassword()))).thenReturn(loginRequest.getUserPassword());
////
////        assertEquals(userService.login(loginRequest).getUserId(), loginRequest.getUserId());
////    }
//}