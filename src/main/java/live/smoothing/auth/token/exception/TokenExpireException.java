package live.smoothing.auth.token.exception;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class TokenExpireException extends CommonException {
    public TokenExpireException() {
        super(HttpStatus.BAD_REQUEST, "Token is expired");
    }
}
