package live.smoothing.auth.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 로그인 요청을 위한 DTO
 *
 * @author 우혜승
 */
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    private String userId;
    private String userPassword;
}
