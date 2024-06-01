package live.smoothing.auth.rabbitmq.service.impl;

import live.smoothing.auth.rabbitmq.dto.CertificationMessage;
import live.smoothing.auth.rabbitmq.service.MessageProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Message Queue에 메세지를 전달하는 클래스
 * routing key를 이용해 routing 된 queue로 메세지를 보낸다.
 *
 * @author 김지윤
 */
@Slf4j
@RequiredArgsConstructor
@Service("messageProducerService")
public class MessageProducerServiceImpl implements MessageProducerService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendMessage(CertificationMessage certificationMessage) {

        rabbitTemplate.convertAndSend(exchangeName, routingKey, certificationMessage);
    }
}
