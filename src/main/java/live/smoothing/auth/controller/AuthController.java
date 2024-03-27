package live.smoothing.auth.controller;

import live.smoothing.auth.token.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final RefreshTokenService refreshTokenService;

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("X-USER-ID") String userId) {
//        refreshTokenService.delete(userId, );

        return ResponseEntity.ok().build();
    }
}
