package live.smoothing.auth.password.service.impl;

import live.smoothing.auth.password.dto.PasswordDto;
import live.smoothing.auth.password.service.PasswordEncodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 비밀번호 암호화 기능을 가지는 인터페이스의 구현체
 *
 * @author 김지윤
 */
@RequiredArgsConstructor
@Service("passwordEncodingService")
public class PasswordEncodingServiceImpl implements PasswordEncodingService {

    private final PasswordEncoder passwordEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public PasswordDto encodePassword(PasswordDto originalPassword) {

        String encodedPassword = passwordEncoder.encode(originalPassword.getPassword());

        return new PasswordDto(encodedPassword);
    }
}
