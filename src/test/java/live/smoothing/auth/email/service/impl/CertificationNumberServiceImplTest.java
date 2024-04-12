package live.smoothing.auth.email.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CertificationNumberServiceImplTest {

    @Mock
    private RedisTemplate<String, String> mailRedisTemplate;

    @Mock
    private ValueOperations<String, String> valueOperationsMock;

    @InjectMocks
    private CertificationNumberServiceImpl certificationNumberService;

    private static String email = "test@gmail.com";
    private static String certificationNumber = "123456";
    @Test
    void saveCertificationNumber() {

        when(mailRedisTemplate.opsForValue()).thenReturn(valueOperationsMock);

        certificationNumberService.saveCertificationNumber(email, certificationNumber);

        verify(valueOperationsMock).set(email, certificationNumber, Duration.ofSeconds(180L));
    }

    @Test
    void getCertificationNumber() {

        when(mailRedisTemplate.opsForValue()).thenReturn(valueOperationsMock);

        when(valueOperationsMock.get(email)).thenReturn(certificationNumber);

        String actualCertificationNumber = certificationNumberService.getCertificationNumber(email);

        assertEquals(certificationNumber, actualCertificationNumber);
    }

    @Test
    void removeCertificationNumber() {

        certificationNumberService.removeCertificationNumber(email);

        boolean emailExists = certificationNumberService.hasKey(email);

        assertFalse(emailExists);
    }

    @Test
    void hasKey() {

        when(mailRedisTemplate.hasKey(email)).thenReturn(true);

        boolean keyExists = certificationNumberService.hasKey(email);

        assertTrue(keyExists);
    }
}