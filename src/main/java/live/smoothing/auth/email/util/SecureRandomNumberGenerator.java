package live.smoothing.auth.email.util;

import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 6자리 수를 랜덤으로 생성해주는 클래스
 *
 * @author 김지윤
 */
@Component
public class SecureRandomNumberGenerator implements RandomNumberGenerator {

    @Override
    public int generateSecureRandomNumber(int maxDigits) throws NoSuchAlgorithmException {

        int bound = (int) Math.pow(10, maxDigits);
        return SecureRandom.getInstanceStrong().nextInt(bound);
    }
}
