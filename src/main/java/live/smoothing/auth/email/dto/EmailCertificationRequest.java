package live.smoothing.auth.email.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이메일 인증을 요청하는 DTO
 *
 * @author 김지윤
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailCertificationRequest {

    private String email;
}
