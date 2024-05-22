package live.smoothing.auth.token.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtPropertiesTest {

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    void getAccessTokenExpirationTime() {
        assertNotNull(jwtProperties.getAccessTokenExpirationTime());
    }

    @Test
    void getRefreshTokenExpirationTime() {
        assertNotNull(jwtProperties.getRefreshTokenExpirationTime());
    }

    @Test
    void getTokenPrefix() {
        assertNotNull(jwtProperties.getTokenPrefix());
    }
}