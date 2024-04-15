package live.smoothing.auth.email.util;

import java.security.NoSuchAlgorithmException;

/**
 * 6자리 수를 랜덤으로 생성해주는 인터페이스
 *
 * @author 김지윤
 */
public interface RandomNumberGenerator {

    int generateSecureRandomNumber(int maxDigits) throws NoSuchAlgorithmException;
}
