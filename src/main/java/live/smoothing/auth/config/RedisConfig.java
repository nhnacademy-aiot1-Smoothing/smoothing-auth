package live.smoothing.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis 설정 클래스
 *
 * @author 김지윤, 우혜승, 하지현
 */
@RequiredArgsConstructor
@Configuration
public class RedisConfig {

    private final RedisProperties redisProperties;
    @Value("${redis.dbIndex}")
    private Integer dbIndex;

    /**
     * Redis Connection 설정하는 Bean
     *
     * @return Redis Connection 설정이 들어간 Factory
     * @author 김지윤, 우혜승, 하지현
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        redisStandaloneConfiguration.setDatabase(dbIndex);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    /**
     * Email 인증번호 저장을 위한 Redis Connection 설정하는 Bean
     *
     * @return Redis Connection 설정이 들어간 Factory
     * @author 김지윤
     */
    @Bean
    public RedisConnectionFactory mailRedisConnectionFactory() {

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        redisStandaloneConfiguration.setDatabase(35);

        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        factory.afterPropertiesSet();

        return factory;
    }

    /**
     * Redis에 key, value 등과 관련된 연산을 하기 위해 설정하는 Bean
     * Redis의 key와 value를 다룰 수 있음
     *
     * @return Redis 연산을 수행하기 위한 RedisTemplate 객체
     * @author 김지윤, 우혜승, 하지현
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate() {

        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * Redis에 key, value 등과 관련된 연산을 하기 위해 설정하는 Bean
     * Redis의 key와 value를 다룰 수 있음
     *
     * @return Redis 연산을 수행하기 위한 RedisTemplate 객체
     * @author 김지윤
     */
    @Bean("mailRedisTemplate")
    public RedisTemplate<String, String> mailRedisTemplate() {

        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(mailRedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
