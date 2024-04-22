package live.smoothing.auth.token.service;

import live.smoothing.auth.token.dto.LoginTokenResponse;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.user.domain.User;

/**
 * 사용자의 로그인, 로그아웃, 토큰 재발급 기능을 제공하는 서비스 인터페이스
 *
 * @author 김지윤, 우혜승, 하지현
 */
public interface TokenService {

    /**
     * 사용자에게 접근할 수 있는 엑세스 토큰과 리프레시 토큰을 발급
     *
     * @param user User - 토큰을 발급할 사용자
     * @return 발급된 토큰 정보를 담은 응답 DTO
     * @author 우혜승
     */
    LoginTokenResponse issue(User user);

    /**
     * 지정된 사용자의 특정 리프레시 토큰을 삭제
     *
     * @param userId String - 토큰을 삭제할 사용자의 식별자
     * @param refreshToken String - 삭제할 리프레시 토큰
     * @author 김지윤
     */
    void delete(String userId, String refreshToken);

    /**
     * 지정된 사용자의 리프레시 토큰을 사용하여 새로운 액세스 토큰을 발급
     *
     * @param userId String - 새로운 토큰을 발급할 사용자의 식별자
     * @param refreshToken String - 사용할 리프레시 토큰
     * @return 새로 발급된 액세스 토큰 정보와 토큰타입을 담은 응답 DTO
     * @author 하지현
     */
    ReissueResponse reissue(String userId, String refreshToken);

    void deleteExpiredToken(String userId);
}
