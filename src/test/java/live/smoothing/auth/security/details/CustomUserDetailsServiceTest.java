package live.smoothing.auth.security.details;

import live.smoothing.auth.adapter.UserAdapter;
import live.smoothing.auth.user.dto.SimpleUserResponse;
import live.smoothing.auth.user.exeption.LoginFailException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    @Mock
    private UserAdapter userAdapter;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void loadUserByUsername_existUser() {

        SimpleUserResponse expect = new SimpleUserResponse();
        expect.setUserId("test");
        expect.setUserPassword("1234");
        expect.setUserAuth(List.of("USER"));
        Mockito.when(userAdapter.getSimpleUser(expect.getUserId())).thenReturn(Optional.of(expect));

        UserDetails result = customUserDetailsService.loadUserByUsername(expect.getUserId());
        Assertions.assertAll(
                () -> assertEquals(result.getUsername(), expect.getUserId()),
                () -> assertEquals(result.getPassword(), expect.getUserPassword()),
                () -> assertEquals(result.getAuthorities().size(), expect.getUserAuth().size())
        );
    }

    @Test
    void loadUserByUsername_notExistUser() {

        SimpleUserResponse expect = new SimpleUserResponse();
        Mockito.when(userAdapter.getSimpleUser(expect.getUserId())).thenThrow(LoginFailException.class);

        Assertions.assertThrows(LoginFailException.class,()-> customUserDetailsService.loadUserByUsername(null));
    }
}