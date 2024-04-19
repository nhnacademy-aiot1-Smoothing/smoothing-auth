package live.smoothing.auth.controller;

import live.smoothing.auth.password.dto.PasswordEncodingRequest;
import live.smoothing.auth.password.dto.PasswordEncodingResponse;
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

/**
 * JWT 토큰 로그인, 재발급, 로그아웃을 위한 컨트롤러 클래스
 *
 * @author 김지윤, 우혜승, 하지현
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncodingService passwordEncodingService;

    /**
     * 로그인 요청을 받아서 해당 사용자의 정보를 확인하고, 토큰을 발급해주는 기능
     *
     * @param loginRequest LoginRequest - 로그인 요청 객체
     * @return 로그인 결과를 담은 응답 Dto
     * @author 우혜승
     */
    @PostMapping("/login")
    public ResponseEntity<LoginTokenResponse> login(@RequestBody LoginRequest loginRequest) {

        User user = userService.getUser(loginRequest.getUserId());
        userService.login(loginRequest, user);
        return ResponseEntity.ok(tokenService.issue(user));
    }

    /**
     * JWT 토큰 재발급을 위한 기능
     *
     * @param request RefreshTokenRequest - 토큰 재발급 요청 객체
     * @param userId String - 사용자 식별자
     * @return 토큰 재발급 결과를 담은 응답 DTO
     * @author 하지현
     */
    @PostMapping("/refresh")
    public ResponseEntity<ReissueResponse> reissueToken(@RequestBody RefreshTokenRequest request,
                                                        @RequestHeader("X-USER-ID") String userId) {

        return new ResponseEntity<>(tokenService.reissue(userId, request.getRefreshToken()), HttpStatus.OK);
    }

    /**
     * 로그아웃 요청을 받아서 로그인되어 있는 해당 사용자의 정보를 Redis에서 삭제하기 위한 기능
     *
     * @param request RefreshTokenRequest - 로그아웃 요청 객체
     * @param userId String - 사용자 식별자
     * @return 응답 상태 코드를 나타내는 ResponseEntity
     * @author 김지윤
     */
    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody RefreshTokenRequest request,
                                       @RequestHeader("X-USER-ID") String userId) {

        tokenService.delete(userId, request.getRefreshToken());

        return ResponseEntity.ok().build();
    }

    /**
     * 주어진 원본 비밀번호를 암호화하여 반환하는 기능
     *
     * @param request 암호화할 원본 비밀번호를 담고 있는 객체
     * @return 암호화된 비밀번호를 담은 응답 DTO
     * @author 김지윤
     */
    @PostMapping("/encode")
    public ResponseEntity<PasswordEncodingResponse> encodePassword(@RequestBody PasswordEncodingRequest request) {

        return new ResponseEntity<>(passwordEncodingService.encodePassword(request), HttpStatus.OK);
    }
}
