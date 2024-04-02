package live.smoothing.auth.user.service;

import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.dto.LoginRequest;

/**
 * 사용자 로그인 및 사용자 정보 조회와 같은 기능을 제공하는 서비스 인터페이스
 *
 * @author 우혜승
 */
public interface UserService {

    /**
     * 사용자 로그인을 처리하는 메서드
     *
     * @param request LoginRequest - 로그인 요청 정보를 포함하는 객체
     * @param user User - 로그인을 시도하는 사용자를 나타내는 객체
     * @author 우혜승
     */
    void login(LoginRequest request, User user);

    /**
     * 사용자를 식별자로 조회하는 메서드
     *
     * @param userId String - 조회할 사용자의 식별자
     * @return 조회된 사용자를 나타내는 객체
     * @author 우혜승
     */
    User getUser(String userId);
}
