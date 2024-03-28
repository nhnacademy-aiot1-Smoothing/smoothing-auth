package live.smoothing.auth.controller;

import live.smoothing.auth.password.dto.PasswordDto;
import live.smoothing.auth.password.service.PasswordEncodingService;
import live.smoothing.auth.token.dto.LoginTokenResponse;
import live.smoothing.auth.token.dto.RefreshTokenRequest;
import live.smoothing.auth.token.dto.ReissueResponse;
import live.smoothing.auth.token.service.TokenService;
import live.smoothing.auth.user.domain.User;
import live.smoothing.auth.user.dto.LoginRequest;
import live.smoothing.auth.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncodingService passwordEncodingService;

    @PostMapping("/login")
    public ResponseEntity<LoginTokenResponse> login(@RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(tokenService.issue(userService.login(loginRequest)));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReissueResponse> reissueToken(@RequestBody RefreshTokenRequest request,
                                                        @RequestHeader("X-USER-ID") String userId) {

        return new ResponseEntity<>(tokenService.reissue(userId, request.getRefreshToken()), HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody RefreshTokenRequest request,
                                       @RequestHeader("X-USER-ID") String userId) {

        tokenService.delete(userId, request.getRefreshToken());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/encode")
    public ResponseEntity<PasswordDto> encodePassword(@RequestBody PasswordDto originalPassword) {

        return new ResponseEntity<>(passwordEncodingService.encodePassword(originalPassword), HttpStatus.OK);
    }
}
