package live.smoothing.auth.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt.properties")
public class JwtProperties {

    private String secret;
    private Integer accessTokenExpirationTime;
    private Integer refreshTokenExpirationTime;
    private String tokenPrefix;
    private String headerString;
    private String loginUrl;
}
