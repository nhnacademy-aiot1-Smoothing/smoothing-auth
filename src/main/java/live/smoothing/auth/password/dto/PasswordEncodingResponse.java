package live.smoothing.auth.password.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 암호화 된 비밀번호를 응답하는 DTO
 *
 * @author 김지윤
 */
@Getter
@AllArgsConstructor
public class PasswordEncodingResponse {

    private String encodedPassword;
}
