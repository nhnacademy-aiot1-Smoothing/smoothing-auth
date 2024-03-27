package live.smoothing.auth.security.filter.details;

import live.smoothing.auth.adapter.UserAdapter;
import live.smoothing.auth.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
//    private final UserAdapter userAdapter;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUserDetails(new User("test","1234", List.of("ROLE_USER")));
    }
}
