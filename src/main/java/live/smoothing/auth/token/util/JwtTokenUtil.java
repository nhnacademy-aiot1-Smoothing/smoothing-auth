package live.smoothing.auth.token.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JwtTokenUtil {
    private static String secret;
    @Value("${jwt.secret}")
    public void setSecret(String secret){
        this.secret = secret;
    }

    public static String createToken(String userId, List<String> roles, Integer expireIn){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expireIn);
        return Jwts.builder()
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
