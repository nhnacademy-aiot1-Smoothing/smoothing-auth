package live.smoothing.auth.password.service;

import live.smoothing.auth.password.dto.PasswordDto;

/**
 * user-service 에서 받아 온 원문 비밀번호를 암호화 해주는 인터페이스
 *
 * @author 김지윤
 */
public interface PasswordEncodingService {

    /**
     *
     * @param originalPassword 암호화 되기 전 비밀번호
     * @return 암호화 된 비밀번호
     */

    PasswordDto encodePassword(PasswordDto originalPassword);
}
