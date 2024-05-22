package live.smoothing.auth.rabbitmq.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageDTOTest {

    private final MessageDTO messageDTO = new MessageDTO("email", "title", "eventMessage");

    @Test
    void getEmail() {
        assertEquals("email", messageDTO.getEmail());
    }

    @Test
    void getTitle() {
        assertEquals("title", messageDTO.getTitle());
    }

    @Test
    void getEventMessage() {
        assertEquals("eventMessage", messageDTO.getEventMessage());
    }
}