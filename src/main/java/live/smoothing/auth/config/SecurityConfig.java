package live.smoothing.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.auth.adapter.UserAdapter;
import live.smoothing.auth.properties.JwtProperties;
import live.smoothing.auth.security.filter.JwtAuthenticationFilter;
import live.smoothing.auth.security.filter.details.CustomUserDetailsService;
import live.smoothing.auth.token.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final ObjectMapper objectMapper;
    private final JwtProperties jwtProperties;
//    private final UserAdapter userAdapter;
    private final RefreshTokenRepository refreshTokenRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterAt(new JwtAuthenticationFilter(authenticationManager(null), objectMapper, jwtProperties,refreshTokenRepository), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .anyRequest().permitAll();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService(){

//        return new CustomUserDetailsService(userAdapter);
        return new CustomUserDetailsService();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return NoOpPasswordEncoder.getInstance();
    }
}
