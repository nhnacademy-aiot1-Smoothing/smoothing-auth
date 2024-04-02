package live.smoothing.auth.user.exeption;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

/**
 * 사용자 서버 오류를 나타내는 예외 클래스
 *
 * @author 우혜승
 */
public class UserServerError extends CommonException {
    public UserServerError() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "User Server Error");
    }
}
