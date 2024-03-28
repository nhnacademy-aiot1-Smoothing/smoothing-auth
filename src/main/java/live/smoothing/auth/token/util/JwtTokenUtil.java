package live.smoothing.auth.token.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import live.smoothing.auth.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {
    private static String secret;

    @Autowired
    public void setSecret(JwtProperties jwtProperties){
        this.secret = jwtProperties.getSecret();
    }

    public static String createToken(String userId, List<String> roles, Integer expireIn){
        System.out.println(secret);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expireIn*60);
        return Jwts.builder()
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
