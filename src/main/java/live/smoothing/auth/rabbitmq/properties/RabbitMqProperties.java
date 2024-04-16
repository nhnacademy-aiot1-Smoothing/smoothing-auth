package live.smoothing.auth.rabbitmq.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit MQ 접속을 위한 속성 클래스
 *
 * @author 김지윤
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualHost;
}
