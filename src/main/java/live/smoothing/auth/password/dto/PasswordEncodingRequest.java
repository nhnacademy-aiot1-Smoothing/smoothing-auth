package live.smoothing.auth.password.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 비밀번호 암호화를 요청하는 DTO
 *
 * @author 김지윤
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordEncodingRequest {

    private String password;
}
