package live.smoothing.auth.rabbitmq.service;

import live.smoothing.auth.rabbitmq.dto.MessageDTO;

/**
 * Message Queue에 메세지를 전달하는 인터페이스
 *
 * @author 김지윤
 */
public interface MessageProducerService {

    /**
     * Message Queue에 메세지를 전달하는 메서드
     *
     * @param messageDTO queue에 전달할 메세지를 담고 있는 객체
     */
    void sendMessage(MessageDTO messageDTO);
}
