package live.smoothing.auth.rabbitmq.service.impl;

import live.smoothing.auth.rabbitmq.dto.MessageDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
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
        MessageDTO messageDTO = new MessageDTO("email", "title", "eventMessage");

        ReflectionTestUtils.setField(messageProducerService, "exchangeName", exchangeName);
        ReflectionTestUtils.setField(messageProducerService, "routingKey", routingKey);

        messageProducerService.sendMessage(messageDTO);

        verify(rabbitTemplate).convertAndSend(exchangeName, routingKey, messageDTO);
    }
}