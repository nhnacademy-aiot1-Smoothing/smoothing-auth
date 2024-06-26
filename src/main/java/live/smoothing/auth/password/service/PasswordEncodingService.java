package live.smoothing.auth.password.service;

import live.smoothing.auth.password.dto.PasswordEncodingRequest;
import live.smoothing.auth.password.dto.PasswordEncodingResponse;

/**
 * user-service 에서 받아 온 원문 비밀번호를 암호화 해주는 인터페이스
 *
 * @author 김지윤
 */
public interface PasswordEncodingService {

    /**
     *
     * @param request 암호화 되기 전 비밀번호를 담고 있는 객체
     * @return 암호화 된 비밀번호
     */

    PasswordEncodingResponse encodePassword(PasswordEncodingRequest request);
}
