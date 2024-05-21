package live.smoothing.auth.email.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SecureRandomNumberGenerator.class)
class SecureRandomNumberGeneratorTest {

    @Autowired
    private RandomNumberGenerator randomNumberGenerator;

    @Test
    void generateSecureRandomNumber() throws Exception {
        assertTrue(randomNumberGenerator.generateSecureRandomNumber(6) >= 99999);
    }
}