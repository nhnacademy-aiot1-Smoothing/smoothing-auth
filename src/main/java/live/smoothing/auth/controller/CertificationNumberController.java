package live.smoothing.auth.controller;

import live.smoothing.auth.email.dto.CertificationNumberResponse;
import live.smoothing.auth.email.dto.EmailCertificationRequest;
import live.smoothing.auth.email.dto.VerificationRequest;
import live.smoothing.auth.email.service.CertificationNumberIssueService;
import live.smoothing.auth.email.service.EmailVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

/**
 * 이메일 인증번호 발급, 검증을 위한 컨트롤러 클래스
 *
 * @author 김지윤
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class CertificationNumberController {

    private final CertificationNumberIssueService certificationNumberIssueService;
    private final EmailVerifyService emailVerifyService;

    /**
     * 요청으로 사용자 email을 받아서 인증번호를 발급해주는 기능
     *
     * @param request 사용자 email을 담은 DTO
     * @return 인증번호를 담은 DTO
     */
    @PostMapping("/email")
    public ResponseEntity<CertificationNumberResponse> issueCertificationNumber(@RequestBody EmailCertificationRequest request) throws NoSuchAlgorithmException {

        return ResponseEntity.ok().body(certificationNumberIssueService.issueCertificationNumber(request.getUserEmail()));
    }

    /**
     * 요청으로 사용자 email과 인증번호를 받아 검증해주는 기능
     *
     * @param request 사용자 email과 인증번호를 담은 DTO
     * @return 인증완료되면 true 실패하면 false
     */
    @PostMapping("/email/verify")
    public ResponseEntity<Boolean> verifyEmail(@RequestBody VerificationRequest request) {

        return ResponseEntity.ok().body(emailVerifyService.isVerifiedEmail(request));
    }
}
