package live.smoothing.auth.email.service.impl;

import live.smoothing.auth.email.dto.VerificationRequest;
import live.smoothing.auth.email.exception.CertificationNumberNotValid;
import live.smoothing.auth.email.service.CertificationNumberService;
import live.smoothing.auth.email.service.EmailVerifyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailVerifyServiceImplTest {

    @Mock
    private CertificationNumberService certificationNumberService;

    @InjectMocks
    private EmailVerifyServiceImpl emailVerifyService;

    @Test
    void isVerifiedEmail() {

        VerificationRequest request = new VerificationRequest("test@gmail.com", "123456");

        when(certificationNumberService.getCertificationNumber(request.getEmail())).thenReturn(request.getCertificationNumber());
        when(certificationNumberService.hasKey(request.getEmail())).thenReturn(true);

        boolean isVerified = emailVerifyService.isVerifiedEmail(request);

        assertTrue(isVerified);
    }

    @Test
    void isNotVerifiedEmail() {

        VerificationRequest request = new VerificationRequest("test@gmail.com", "123456");

        when(certificationNumberService.getCertificationNumber(request.getEmail())).thenReturn("999999");
        when(certificationNumberService.hasKey(request.getEmail())).thenReturn(true);

        assertThrows(CertificationNumberNotValid.class, () -> {
            emailVerifyService.isVerifiedEmail(request);
        });
    }

}