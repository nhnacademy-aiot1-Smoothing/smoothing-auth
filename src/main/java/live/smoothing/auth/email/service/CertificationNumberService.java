package live.smoothing.auth.email.service;

/**
 * 이메일과 인증번호를 redis에 저장, 조회, 삭제하기 위한 인터페이스
 *
 * @author 김지윤
 */
public interface CertificationNumberService {

    /**
     * 사용자의 email과 인증번호를 redis에 저장
     *
     * @param email 사용자의 email
     * @param certificationNumber 사용자가 발급받은 인증번호
     */
    void saveCertificationNumber(String email, String certificationNumber);

    /**
     * 사용자의 email로 발급된 인증번호 조회
     *
     * @param email 사용자의 email
     * @return 사용자가 발급받은 인증번호
     */
    String getCertificationNumber(String email);

    /**
     * 사용자의 email로 발급된 인증번호 삭제
     *
     * @param email 사용자의 email
     */
    void removeCertificationNumber(String email);

    /**
     * 사용자의 email로 발급된 인증번호가 있는지 확인
     *
     * @param email 사용자의 email
     * @return 인증번호가 있다면 true, 없으면 false 반환
     */
    boolean hasKey(String email);
}
