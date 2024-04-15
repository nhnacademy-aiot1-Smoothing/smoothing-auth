package live.smoothing.auth.email.service.impl;

import live.smoothing.auth.email.service.CertificationNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * 이메일과 인증번호를 redis에 저장, 조회, 삭제하기 위한 클래스
 *
 * @author 김지윤
 */
@RequiredArgsConstructor
@Service("certificationNumberService")
public class CertificationNumberServiceImpl implements CertificationNumberService {

    private final RedisTemplate<String, String> mailRedisTemplate;

    private final Long VALID_TIME = 180L; // 인증번호 유효시간 3분으로 설정

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCertificationNumber(String email, String certificationNumber) {

        mailRedisTemplate.opsForValue().set(email, certificationNumber, Duration.ofSeconds(VALID_TIME));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCertificationNumber(String email) {

        return mailRedisTemplate.opsForValue().get(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCertificationNumber(String email) {

        mailRedisTemplate.delete(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasKey(String email) {

        return Boolean.TRUE.equals(mailRedisTemplate.hasKey(email));
    }
}
