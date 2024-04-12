package live.smoothing.auth.email.exception;

import live.smoothing.common.exception.CommonException;
import org.springframework.http.HttpStatus;

/**
 * 인증번호가 유효하지 않을 때 발생하는 예외 클래스
 *
 * @author 김지윤
 */
public class CertificationNumberNotValid extends CommonException {

    public CertificationNumberNotValid() {

        super(HttpStatus.UNAUTHORIZED, "CertificationNumber Error!");
    }
}
