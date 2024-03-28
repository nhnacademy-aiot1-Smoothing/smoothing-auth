package live.smoothing.auth.password.service.impl;

import live.smoothing.auth.password.dto.PasswordDto;
import live.smoothing.auth.password.service.PasswordEncodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("passwordEncodingService")
public class PasswordEncodingServiceImpl implements PasswordEncodingService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public PasswordDto encodePassword(PasswordDto originalPassword) {

        String encodedPassword = passwordEncoder.encode(originalPassword.getPassword());

        return new PasswordDto(encodedPassword);
    }
}
