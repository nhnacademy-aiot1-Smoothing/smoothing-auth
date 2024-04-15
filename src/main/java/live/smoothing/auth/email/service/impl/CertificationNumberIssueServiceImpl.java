package live.smoothing.auth.email.service.impl;

import live.smoothing.auth.email.dto.CertificationNumberResponse;
import live.smoothing.auth.email.service.CertificationNumberIssueService;
import live.smoothing.auth.email.service.CertificationNumberService;
import live.smoothing.auth.email.util.CertificationNumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * 이메일 인증번호를 발급해주는 클래스
 * 인증번호 발급 후 이메일과 인증번호를 redis에 추가
 *
 * @author 김지윤
 */
@RequiredArgsConstructor
@Service("certificationNumberIssueService")
public class CertificationNumberIssueServiceImpl implements CertificationNumberIssueService {

    private final CertificationNumberUtil certificationNumberUtil;
    private final CertificationNumberService certificationNumberService;

    /**
     * {@inheritDoc}
     */
    @Override
    public CertificationNumberResponse issueCertificationNumber(String email) throws NoSuchAlgorithmException {

        String certificationNumber = certificationNumberUtil.createCertificationNumber();
        certificationNumberService.saveCertificationNumber(email, certificationNumber);

        return new CertificationNumberResponse(certificationNumber);
    }
}
