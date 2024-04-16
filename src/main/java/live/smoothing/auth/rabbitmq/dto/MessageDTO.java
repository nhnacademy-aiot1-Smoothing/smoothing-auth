package live.smoothing.auth.rabbitmq.dto;

import lombok.*;

/**
 *  Message Queue로 전달할 메세지 DTO
 *
 * @author 김지윤
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class MessageDTO {

    private String email;
    private String title;
    private String eventMessage;
}
