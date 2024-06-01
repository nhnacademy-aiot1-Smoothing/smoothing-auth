package live.smoothing.auth.rabbitmq.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CertificationMessageTest {

    private final CertificationMessage certificationMessage = new CertificationMessage("email", "12345");

    @Test
    void getEmail() {
        assertEquals("email", certificationMessage.getEmail());
    }

    @Test
    void getCertificationNumber() {
        assertEquals("12345", certificationMessage.getCertificationNumber());
    }

}