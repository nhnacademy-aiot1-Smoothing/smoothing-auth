package live.smoothing.auth.password.service.impl;

import live.smoothing.auth.password.dto.PasswordDto;
import live.smoothing.auth.password.service.PasswordEncodingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordEncodingServiceImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PasswordEncodingServiceImpl passwordEncodingService;
    @Test
    void encodePassword() {
        String originalPassword = "originalPassword";
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(originalPassword)).thenReturn(encodedPassword);

        PasswordDto originalPasswordDto = new PasswordDto(originalPassword);
        PasswordDto encodedPasswordDto = passwordEncodingService.encodePassword(originalPasswordDto);

        assertEquals(encodedPassword, encodedPasswordDto.getPassword());
    }
}