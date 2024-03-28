package live.smoothing.auth.user.exeption;

import live.smoothing.auth.exception.ErrorException;

public class LoginFailException extends ErrorException {
    public LoginFailException(String message) {

        super(400, LoginFailException.class, message);
    }
}
