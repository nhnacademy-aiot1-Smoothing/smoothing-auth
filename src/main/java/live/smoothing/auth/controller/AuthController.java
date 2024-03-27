package live.smoothing.auth.controller;

import live.smoothing.auth.token.dto.RefreshTokenRequest;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity<ReissueResponse> reissueToken(@RequestBody RefreshTokenRequest request,
                                                        @RequestHeader("X-USER-ID") String userId) {

        return new ResponseEntity<>(refreshTokenService.reissue(userId, request.getRefreshToken()), HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody RefreshTokenRequest request,
                                       @RequestHeader("X-USER-ID") String userId) {

        refreshTokenService.delete(userId, request.getRefreshToken());

        return ResponseEntity.ok().build();
    }
}
