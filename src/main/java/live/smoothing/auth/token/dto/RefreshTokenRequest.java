package live.smoothing.auth.token.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 로그아웃, 토큰 재발급 요청을 위한 DTO
 *
 * @author 하지현
 */
@Getter
@Setter
public class RefreshTokenRequest {
    private String refreshToken;
}
