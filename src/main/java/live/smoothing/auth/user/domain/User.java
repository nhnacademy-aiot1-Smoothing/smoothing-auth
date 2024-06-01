package live.smoothing.auth.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 사용자 ID, PW, 권한을 포함한 사용자 도메인 클래스
 *
 * @author 우혜승
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String userPassword;
    private List<String> roles;
}
