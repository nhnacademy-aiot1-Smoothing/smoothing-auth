package live.smoothing.auth.token.exeption;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotExist extends CommonException {
    public RefreshTokenNotExist() {
        super(HttpStatus.NOT_FOUND, "Refresh Token is Not exist");
    }
}
