package live.smoothing.auth.user.exeption;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

/**
 * 비밀번호가 유효하지 않을 때 발생하는 예외 클래스
 *
 * @author 우혜승
 */
public class PasswordNotValid extends CommonException {
    public PasswordNotValid() {
        super(HttpStatus.UNAUTHORIZED, "Password Error");
    }
}
