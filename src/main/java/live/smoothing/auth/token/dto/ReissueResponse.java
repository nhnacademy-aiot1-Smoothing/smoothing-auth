package live.smoothing.auth.token.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 토큰 재발급 성공 후 새로운 토큰과 타입을 담는 DTO
 *
 * @author 하지현
 */
@Setter
@Getter
public class ReissueResponse {
    private String accessToken;
    private String tokenType;
}
