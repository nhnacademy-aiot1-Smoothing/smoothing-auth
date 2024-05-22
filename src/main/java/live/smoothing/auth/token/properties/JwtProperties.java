package live.smoothing.auth.token.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Jwt 토큰 속성 클래스
 *
 * @author 우혜승
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt.properties")
public class JwtProperties {

    private String secret;
    private Integer accessTokenExpirationTime;
    private Integer refreshTokenExpirationTime;
    private String tokenPrefix;
}
