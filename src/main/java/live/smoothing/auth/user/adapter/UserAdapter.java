package live.smoothing.auth.user.adapter;

import live.smoothing.auth.user.dto.SimpleUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(value = "user-service",path = "/api/user")
public interface UserAdapter {
    @GetMapping("/simples")
    Optional<SimpleUserResponse> getSimpleUser(@RequestHeader("X-USER-ID") String userId);
}
