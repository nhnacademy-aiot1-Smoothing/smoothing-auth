package live.smoothing.auth.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class User {
    private String userId;
    private String userPassword;
    private Collection<GrantedAuthority> authorities;
}
