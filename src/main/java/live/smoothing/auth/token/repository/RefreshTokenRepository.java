package live.smoothing.auth.token.repository;

import live.smoothing.auth.token.entity.RefreshToken;

import java.util.List;

/**
 * redis에서 refreshToken을 관리하는 클래스
 *
 * @author 김지윤, 우혜승, 하지현
 */
public interface RefreshTokenRepository {

    /**
     * refreshToken을 redis에 저장
     *
     * @param refreshToken refreshToken
     */
    void save(RefreshToken refreshToken);

    /**
     * userId, refreshToken으로 존재 여부 확인
     *
     * @param userId 사용자 Id
     * @param refreshToken refreshToken
     * @return refreshToken이 존재한다면 true, 없으면 false 반환
     */
    boolean existByUserIdAndRefreshToken(String userId, String refreshToken);

    /**
     * userId, refreshToken으로 redis에서 refreshToken 삭제
     *
     * @param userId 사용자 Id
     * @param refreshToken refreshToken
     */
    void deleteByUserIdAndRefreshToken(String userId, String refreshToken);

    List<String> findAllByUserId(String userId);
}
