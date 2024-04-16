package live.smoothing.auth.config;

import live.smoothing.auth.rabbitmq.properties.RabbitMqProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit MQ 설정 클래스
 *
 * @author 김지윤
 */
@Getter
@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig {

    private final RabbitMqProperties rabbitMqProperties;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    /**
     * Queue 생성
     *
     * @return 생성된 Queue
     */
    @Bean
    public Queue queue() {

        return new Queue(queueName);
    }

    /**
     * Exchange 생성
     *
     * @return 생성된 Exchange
     */
    @Bean
    public DirectExchange directExchange() {

        return new DirectExchange(exchangeName);
    }

    /**
     * routing key로 queue를 exchange에 binding
     *
     * @param queue queue
     * @param exchange exchange
     * @return 생성된 Binding
     */
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /**
     * Rabbit MQ와의 연결을 설정
     *
     * @return MQ Connection 설정이 들어간 Factory
     */
    @Bean
    public CachingConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitMqProperties.getHost());
        connectionFactory.setPort(rabbitMqProperties.getPort());
        connectionFactory.setUsername(rabbitMqProperties.getUsername());
        connectionFactory.setPassword(rabbitMqProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMqProperties.getVirtualHost());

        return connectionFactory;
    }

    /**
     * Rabbit MQ와의 상호 작용을 위한 template
     *
     * @param connectionFactory MQ Connection 설정이 들어간 Factory
     * @return rabbitTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

        return rabbitTemplate;
    }

    /**
     * Rabbit MQ 메세지를 JSON 형식으로 변환하기 위한 messageConverter
     *
     * @return messageConverter
     */
    @Bean
    public MessageConverter jackson2JsonMessageConverter() {

        return new Jackson2JsonMessageConverter();
    }
}
