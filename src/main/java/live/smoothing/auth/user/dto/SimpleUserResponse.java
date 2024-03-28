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

    private String userId;
    private String userPassword;
    private List<String> userAuth;

    public User toEntity(){
        return new User(userId,userPassword,userAuth);
    }
}
