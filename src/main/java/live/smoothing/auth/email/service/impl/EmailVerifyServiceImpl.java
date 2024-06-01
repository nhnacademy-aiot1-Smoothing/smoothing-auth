package live.smoothing.auth.email.service.impl;

import live.smoothing.auth.email.dto.VerificationRequest;
import live.smoothing.auth.email.exception.CertificationNumberNotValid;
import live.smoothing.auth.email.service.CertificationNumberService;
import live.smoothing.auth.email.service.EmailVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 이메일과 인증번호를 검증하는 클래스
 *
 * @author 김지윤
 */
@RequiredArgsConstructor
@Service("emailVerifyService")
public class EmailVerifyServiceImpl implements EmailVerifyService {

    private final CertificationNumberService certificationNumberService;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVerifiedEmail(VerificationRequest request) {

        if(!isEmailAndCertificationNumberExists(request)) {
            throw new CertificationNumberNotValid();
        }

        certificationNumberService.removeCertificationNumber(request.getUserEmail());

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmailAndCertificationNumberExists(VerificationRequest request) {

        boolean emailExists = isEmailExists(request.getUserEmail());

        return (emailExists && certificationNumberService.getCertificationNumber(request.getUserEmail()).equals(request.getCertificationNumber()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmailExists(String email) {

        return certificationNumberService.hasKey(email);
    }
}
