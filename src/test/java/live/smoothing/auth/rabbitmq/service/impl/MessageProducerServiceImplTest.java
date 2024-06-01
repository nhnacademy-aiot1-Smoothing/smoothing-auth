package live.smoothing.auth.rabbitmq.service.impl;

import live.smoothing.auth.rabbitmq.dto.CertificationMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageProducerServiceImplTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private MessageProducerServiceImpl messageProducerService;

    private final String exchangeName = "exchangeName";

    private final String routingKey = "routingKey";

    @Test
    void sendMessage() {
        CertificationMessage certificationMessage = new CertificationMessage("email", "12345");

        ReflectionTestUtils.setField(messageProducerService, "exchangeName", exchangeName);
        ReflectionTestUtils.setField(messageProducerService, "routingKey", routingKey);

        messageProducerService.sendMessage(certificationMessage);

        verify(rabbitTemplate).convertAndSend(exchangeName, routingKey, certificationMessage);
    }
}