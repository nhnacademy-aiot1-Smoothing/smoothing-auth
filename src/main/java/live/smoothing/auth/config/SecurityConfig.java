package live.smoothing.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 설정 클래스
 *
 * @author 김지윤
 */
@Configuration
public class SecurityConfig {

    @Bean
    @Profile("!dev")
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile("dev")
    public PasswordEncoder passwordEncoderDev() {
        return NoOpPasswordEncoder.getInstance();
    }
}
