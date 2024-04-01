package live.smoothing.auth.token.exeption;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

/**
 * RefreshToken 을 찾을 수 없을 때 발생하는 예외
 *
 * @author 우혜승
 */
public class RefreshTokenNotExist extends CommonException {
    public RefreshTokenNotExist() {
        super(HttpStatus.NOT_FOUND, "Refresh Token is Not exist");
    }
}
