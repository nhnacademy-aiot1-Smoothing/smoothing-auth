package live.smoothing.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import live.smoothing.auth.user.domain.User;

import java.util.List;

/**
 * 사용자 정보 및 권한 목록을 담는 사용자 DTO
 *
 * @author 우혜승
 */
public class SimpleUserResponse {

    @JsonProperty("user")
    private InnerUserDto user;
    @JsonProperty("roles")
    private List<String> roles;

    /**
     * entity로 변환
     */
    public User toEntity() {
        return new User(user.getUserId(), user.getUserPassword(), roles);
    }

    /**
     * 사용자 정보를 담는 내부 클래스
     */
}
