package live.smoothing.auth.token.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RefreshToken {

    private String userId;
    private String refreshToken;
}
