package live.smoothing.auth.security.details;

import live.smoothing.auth.adapter.UserAdapter;
import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.dto.SimpleUserResponse;
import live.smoothing.auth.user.exeption.LoginFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAdapter userAdapter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<SimpleUserResponse> user = userAdapter.getSimpleUser(username);
        if (user.isEmpty()) {
            throw new LoginFailException(username + "을 찾을 수 없습니다.", "api/auth/login");
        }
        return new CustomUserDetails(new User(user.get().getUserId(), user.get().getUserPassword(), user.get().getUserAuth()));
    }
}
