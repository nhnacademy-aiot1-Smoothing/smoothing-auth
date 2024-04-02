package live.smoothing.auth.user.adapter;

import live.smoothing.auth.user.dto.SimpleUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

/**
 * 사용자 서비스와 통신하기 위한 Feign 클라이언트 인터페이스
 *
 * @author 우혜승
 */
@FeignClient(value = "user-service", path = "/api/user")
public interface UserAdapter {

    /**
     * 로그인한 사용자의 간단한 정보를 가져오는 메서드
     *
     * @param userId String - 사용자 식별자
     * @return 사용자의 정보를 포함한 객체
     * @author 우혜승
     */
    @GetMapping("/login")
    ResponseEntity<SimpleUserResponse> getSimpleUser(@RequestHeader("X-USER-ID") String userId);
}