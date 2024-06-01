package live.smoothing.auth.email.service.impl;

import live.smoothing.auth.email.service.CertificationNumberService;
import live.smoothing.auth.email.util.CertificationNumberUtil;
import live.smoothing.auth.rabbitmq.service.MessageProducerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CertificationNumberIssueServiceImplTest {

    @Mock
    private CertificationNumberUtil certificationNumberUtil;

    @Mock
    private CertificationNumberService certificationNumberService;

    @Mock
    private MessageProducerService messageProducerService;

    @InjectMocks
    private CertificationNumberIssueServiceImpl certificationNumberIssueService;

    @Test
    void issueCertificationNumber() throws NoSuchAlgorithmException {

        String email = "test@gmail.com";
        String certificationNumber = "123456";

        when(certificationNumberUtil.createCertificationNumber()).thenReturn(certificationNumber);

       certificationNumberIssueService.issueCertificationNumber(email);

        verify(certificationNumberService, times(1)).saveCertificationNumber(eq(email), anyString());

    }
}