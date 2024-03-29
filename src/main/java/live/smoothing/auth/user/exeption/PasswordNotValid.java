package live.smoothing.auth.user.exeption;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

public class PasswordNotValid extends CommonException {
    public PasswordNotValid() {
        super(HttpStatus.UNAUTHORIZED, "Password Error");
    }
}
