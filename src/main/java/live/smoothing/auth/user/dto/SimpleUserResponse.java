package live.smoothing.auth.user.dto;

import live.smoothing.auth.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 사용자 정보 및 권한 목록을 담는 사용자 DTO
 *
 * @author 우혜승
 */
@Getter
@Setter
@NoArgsConstructor
public class SimpleUserResponse {

    private InnerUser user;
    private List<String> auths;

    /**
     * entity로 변환
     */
    public User toEntity() {
        return new User(user.userId, user.userPassword, auths);
    }

    /**
     * 사용자 정보를 담는 내부 클래스
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class InnerUser {
        private String userId;
        private String userPassword;
    }
}
