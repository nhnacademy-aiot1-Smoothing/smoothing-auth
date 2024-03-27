package live.smoothing.auth.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.auth.properties.JwtProperties;
import live.smoothing.auth.security.filter.details.CustomUserDetails;
import live.smoothing.auth.token.dto.LoginTokenResponse;
import live.smoothing.auth.token.entity.RefreshToken;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import live.smoothing.auth.token.util.JwtTokenUtil;
import live.smoothing.auth.user.dto.LoginRequest;
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
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, JwtProperties jwtProperties, RefreshTokenRepository refreshTokenRepository) {

        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.jwtProperties = jwtProperties;
        this.refreshTokenRepository = refreshTokenRepository;

        setFilterProcessesUrl(jwtProperties.getLoginUrl());
    }

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
        String accessToken = JwtTokenUtil.createToken(userDetails.getUsername(),
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                jwtProperties.getAccessTokenExpirationTime());

        calendar.add(Calendar.SECOND, -jwtProperties.getAccessTokenExpirationTime() + jwtProperties.getRefreshTokenExpirationTime());
        String refreshToken = JwtTokenUtil.createToken(userDetails.getUsername(),
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()),
                jwtProperties.getRefreshTokenExpirationTime());

        LoginTokenResponse loginTokenResponse = new LoginTokenResponse(accessToken, refreshToken, jwtProperties.getTokenPrefix());
        String result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(loginTokenResponse);
        response.setHeader("Content-Type", "application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(result);
        printWriter.close();
        refreshTokenRepository.save(new RefreshToken(userDetails.getUsername(),refreshToken));
    }
}
