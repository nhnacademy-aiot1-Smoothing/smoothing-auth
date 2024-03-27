package live.smoothing.auth.controller;

import live.smoothing.auth.token.dto.RefreshTokenRequest;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
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
                                                        @RequestHeader("X-USER-Id") String userId) {

        return new ResponseEntity<>(refreshTokenService.reissue(userId, request.getRefreshToken()), HttpStatus.OK);
    }
}
