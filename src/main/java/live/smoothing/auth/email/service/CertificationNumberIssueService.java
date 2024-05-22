package live.smoothing.auth.email.service;

import java.security.NoSuchAlgorithmException;

/**
 * 이메일 인증번호를 발급해주는 인터페이스
 *
 * @author 김지윤
 */
public interface CertificationNumberIssueService {

    /**
     * 이메일 인증번호를 발급해주는 인터페이스
     * @param email 사용자의 email
     * @return 발급된 인증번호
     */
    void issueCertificationNumber(String email) throws NoSuchAlgorithmException;
}
