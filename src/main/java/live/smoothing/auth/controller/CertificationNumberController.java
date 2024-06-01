package live.smoothing.auth.controller;

import live.smoothing.auth.email.dto.EmailCertificationRequest;
import live.smoothing.auth.email.dto.MessageResponse;
import live.smoothing.auth.email.dto.VerificationRequest;
import live.smoothing.auth.email.service.CertificationNumberIssueService;
import live.smoothing.auth.email.service.EmailVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<MessageResponse> issueCertificationNumber(@RequestBody EmailCertificationRequest request) throws NoSuchAlgorithmException {

        certificationNumberIssueService.issueCertificationNumber(request.getUserEmail());

        return ResponseEntity.ok().body(new MessageResponse("인증번호가 발급되었습니다."));
    }

    /**
     * 요청으로 사용자 email과 인증번호를 받아 검증해주는 기능
     *
     * @param request 사용자 email과 인증번호를 담은 DTO
     * @return 인증완료되면 true 실패하면 false
     */
    @PostMapping("/email/verify")
    public ResponseEntity<MessageResponse> verifyEmail(@RequestBody VerificationRequest request) {

        if (emailVerifyService.isVerifiedEmail(request)) {
            return ResponseEntity.ok().body(new MessageResponse("인증번호가 확인되었습니다."));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("인증번호를 다시 확인해주세요"));
    }
}
