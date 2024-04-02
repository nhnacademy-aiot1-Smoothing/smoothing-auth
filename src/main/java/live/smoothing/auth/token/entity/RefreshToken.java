package live.smoothing.auth.token.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Redis 에 refreshToken (key: userId, value: refreshToken) 저장을 위한 entity
 *
 * @author 우혜승
 */
@Getter
@AllArgsConstructor
public class RefreshToken {

    private String userId;
    private String refreshToken;
}
