package live.smoothing.auth.user.dto;

import live.smoothing.auth.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SimpleUserResponse {

    private InnerUser user;
    private List<String> auths;

    public User toEntity() {
        return new User(user.userId, user.userPassword, auths);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class InnerUser {
        private String userId;
        private String userPassword;
    }
}
