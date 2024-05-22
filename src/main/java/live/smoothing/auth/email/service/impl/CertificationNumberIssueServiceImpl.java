package live.smoothing.auth.email.service.impl;

import live.smoothing.auth.email.service.CertificationNumberIssueService;
import live.smoothing.auth.email.service.CertificationNumberService;
import live.smoothing.auth.email.util.CertificationNumberUtil;
import live.smoothing.auth.rabbitmq.dto.MessageDTO;
import live.smoothing.auth.rabbitmq.service.MessageProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * 이메일 인증번호를 발급해주는 클래스
 * 인증번호 발급 후 이메일과 인증번호를 redis에 추가
 * messageDTO에 인증번호 발급 메일에 담을 메세지를 추가한 후 queue에 전달
 *
 * @author 김지윤
 */
@RequiredArgsConstructor
@Service("certificationNumberIssueService")
public class CertificationNumberIssueServiceImpl implements CertificationNumberIssueService {

    private final CertificationNumberUtil certificationNumberUtil;
    private final CertificationNumberService certificationNumberService;
    private final MessageProducerService messageProducerService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void issueCertificationNumber(String email) throws NoSuchAlgorithmException {

        String certificationNumber = certificationNumberUtil.createCertificationNumber();
        certificationNumberService.saveCertificationNumber(email, certificationNumber);

        String title = "인증번호 안내 입니다.";
        String eventMessage = "인증번호는 " + certificationNumber + " 입니다.";

        MessageDTO messageDTO = new MessageDTO(email, title, eventMessage);

        messageProducerService.sendMessage(messageDTO);
    }
}
