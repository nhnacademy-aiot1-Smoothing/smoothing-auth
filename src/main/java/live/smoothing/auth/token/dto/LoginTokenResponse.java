package live.smoothing.auth.token.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 성공 시 accessToken, refreshToken 을 응답하는 DTO
 *
 * @author 우혜승
 */
@Getter
@AllArgsConstructor
public class LoginTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
}
