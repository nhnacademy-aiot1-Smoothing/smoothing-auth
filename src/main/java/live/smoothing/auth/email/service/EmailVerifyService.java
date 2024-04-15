package live.smoothing.auth.email.service;

import live.smoothing.auth.email.dto.VerificationRequest;

/**
 * 사용자가 입력한 인증번호가 유효한지 검증하기 위한 인터페이스
 *
 * @author 김지윤
 */
public interface EmailVerifyService {

    /**
     * 사용자의 email과 인증번호가 유효한지 확인
     *
     * @param request email과 인증번호가 담겨있는 DTO
     */
    boolean isVerifiedEmail(VerificationRequest request);

    /**
     * 사용자의 email로 저장된 인증번호가 존재하는지 확인
     *
     * @param request email과 인증번호가 담겨있는 DTO
     */
    boolean isEmailAndCertificationNumberExists(VerificationRequest request);
    /**
     * 사용자의 email이 존재하는지 확인
     *
     * @param email 사용자의 email
     */
    boolean isEmailExists(String email);
}
