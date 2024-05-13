package live.smoothing.auth.token.exception;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class RefreshAlreadyIssuingException extends CommonException {
    public RefreshAlreadyIssuingException() {
        super(HttpStatus.I_AM_A_TEAPOT, "Refresh token is already being issued");
    }
}
