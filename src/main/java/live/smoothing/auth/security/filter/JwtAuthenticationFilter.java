package live.smoothing.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import live.smoothing.auth.properties.JwtProperties;
import live.smoothing.auth.security.filter.details.CustomUserDetails;
import live.smoothing.auth.token.dto.TokenResponse;
import live.smoothing.auth.user.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final JwtProperties jwtProperties;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequest loginRequest;
        try {
            loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getUserPassword());
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, jwtProperties.getAccessTokenExpirationTime());
        String accessToken = Jwts.builder()
                .claim("userId", userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();

        calendar.add(Calendar.SECOND, -jwtProperties.getAccessTokenExpirationTime() + jwtProperties.getRefreshTokenExpirationTime());
        String refreshToken = Jwts.builder()
                .claim("userId", userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();

        TokenResponse tokenResponse = new TokenResponse(accessToken, refreshToken, jwtProperties.getTokenPrefix());
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(tokenResponse);
        response.setHeader("Content-Type", "application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(result);
        printWriter.close();
    }
}
