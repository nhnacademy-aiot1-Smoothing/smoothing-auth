package live.smoothing.auth.token.exception;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class TokenParseException extends CommonException {
    public TokenParseException() {
        super(HttpStatus.BAD_REQUEST, "Token is invalid");
    }
}
