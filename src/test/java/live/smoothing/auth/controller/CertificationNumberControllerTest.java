package live.smoothing.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import live.smoothing.auth.email.dto.CertificationNumberResponse;
import live.smoothing.auth.email.dto.EmailCertificationRequest;
import live.smoothing.auth.email.dto.MessageResponse;
import live.smoothing.auth.email.dto.VerificationRequest;
import live.smoothing.auth.email.service.CertificationNumberIssueService;
import live.smoothing.auth.email.service.EmailVerifyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CertificationNumberController.class)
@AutoConfigureMockMvc(addFilters = false)
class CertificationNumberControllerTest {

    @MockBean
    private CertificationNumberIssueService certificationNumberIssueService;

    @MockBean
    private EmailVerifyService emailVerifyService;

    @Autowired
    private MockMvc mockMvc;

    private final String email = "test@gmail.com";
    private final String certificationNumber = "123456";

    @Test
    void issueCertificationNumber() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        EmailCertificationRequest request = new EmailCertificationRequest(email);
        MessageResponse response = new MessageResponse("인증번호가 발급되었습니다.");

        doNothing().when(certificationNumberIssueService).issueCertificationNumber(email);

        mockMvc.perform(post("/api/auth/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

    }

    @Test
    void verifyEmail() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        VerificationRequest request = new VerificationRequest(email, certificationNumber);

        when(emailVerifyService.isVerifiedEmail(request)).thenReturn(true);

        mockMvc.perform(post("/api/auth/email/verify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is4xxClientError());
    }
}