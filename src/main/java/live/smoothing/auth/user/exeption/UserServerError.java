package live.smoothing.auth.user.exeption;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class UserServerError extends CommonException {
    public UserServerError() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "User Server Error");
    }
}
