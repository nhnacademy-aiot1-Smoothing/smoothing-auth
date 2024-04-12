package live.smoothing.auth.email.util;

import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 인증번호를 생성하는 클래스
 *
 * @author 김지윤
 */
@Component
public class CertificationNumberUtil {

    public String createCertificationNumber() throws NoSuchAlgorithmException {

        String result;

        do {
            int num = SecureRandom.getInstanceStrong().nextInt(999999);
            result = String.valueOf(num);
        } while(result.length() != 6);

        return result;
    }
}
