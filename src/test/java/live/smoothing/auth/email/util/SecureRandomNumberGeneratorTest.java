package live.smoothing.auth.email.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecureRandomNumberGeneratorTest {

    private final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Test
    void generateSecureRandomNumber() throws Exception {
        assertTrue(randomNumberGenerator.generateSecureRandomNumber(6) > 99999);
    }
}