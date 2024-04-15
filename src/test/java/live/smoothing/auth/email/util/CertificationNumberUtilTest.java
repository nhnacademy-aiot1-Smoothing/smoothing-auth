package live.smoothing.auth.email.util;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CertificationNumberUtilTest {

    @Test
    void createCertificationNumber() throws NoSuchAlgorithmException {

        RandomNumberGenerator randomNumberGenerator = mock(RandomNumberGenerator.class);
        when(randomNumberGenerator.generateSecureRandomNumber(anyInt())).thenReturn(123456);

        CertificationNumberUtil certificationNumberUtil = new CertificationNumberUtil(randomNumberGenerator);

        String certificationNumber = certificationNumberUtil.createCertificationNumber();

        assertEquals(6, certificationNumber.length());
    }
}