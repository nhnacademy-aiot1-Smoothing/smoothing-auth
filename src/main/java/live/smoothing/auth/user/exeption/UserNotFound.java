package live.smoothing.auth.user.exeption;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class UserNotFound extends CommonException {
    public UserNotFound() {
        super(HttpStatus.NOT_FOUND, "User Not Found");
    }
}
