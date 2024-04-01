package live.smoothing.auth.token.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import live.smoothing.auth.token.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * JWT 토큰 유틸리티 클래스
 *
 * @author 우혜승
 */
@Component
public class JwtTokenUtil {

    private static String secret;

    /**
     * JWT 프로퍼티로부터 시크릿 값을 설정
     *
     * @param jwtProperties JwtProperties - JWT 설정 프로퍼티
     * @author 우혜승
     */
    @Autowired
    public void setSecret(JwtProperties jwtProperties) {

        this.secret = jwtProperties.getSecret();
    }

    /**
     * 주어진 사용자 ID, 역할 목록, 만료 시간으로 JWT 토큰을 생성
     *
     * @param userId String - 사용자 Id
     * @param roles List - 사용자 역할 목록
     * @param expireIn Integer - 토큰 만료 시간(분 단위)
     * @return 생성된 JWT 토큰
     * @author 우혜승
     */
    public static String createToken(String userId, List<String> roles, Integer expireIn) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expireIn * 60);
        return Jwts.builder()
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
