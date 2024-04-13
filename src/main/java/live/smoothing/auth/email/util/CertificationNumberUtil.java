package live.smoothing.auth.email.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 인증번호를 생성하는 클래스
 *
 * @author 김지윤
 */
@Component
@RequiredArgsConstructor
public class CertificationNumberUtil {

    private final RandomNumberGenerator randomNumberGenerator;

    public String createCertificationNumber() throws NoSuchAlgorithmException {

        String result;

        do {
            int num = randomNumberGenerator.generateSecureRandomNumber(6);
            result = String.valueOf(num);
        } while(!isCertificationNumberValid(result));

        return result;
    }

    private boolean isCertificationNumberValid(String certificationNumber) {

        return certificationNumber.length() == 6;
    }
}


