package live.smoothing.auth.rabbitmq.dto;

import lombok.*;

/**
 *  Message Queue로 전달할 메세지 DTO
 *
 * @author 김지윤
 */
@Getter
@AllArgsConstructor
public class CertificationMessage {

    private String email;
    private String certificationNumber;

}
