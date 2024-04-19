package live.smoothing.auth.password.dto;

import lombok.*;

/**
 * 비밀번호 암호화를 요청하는 DTO
 *
 * @author 김지윤
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordEncodingRequest {

    private String originalPassword;
}
