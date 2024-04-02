package live.smoothing.auth.user.exeption;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

/**
 * 사용자 정보를 찾을 수 없을 때 발생하는 예외 클래스
 *
 * @author 우혜승
 */
public class UserNotFound extends CommonException {

    public UserNotFound() {

        super(HttpStatus.NOT_FOUND, "User Not Found");
    }
}
