package live.smoothing.auth.token.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * JWT Token 관련 유틸 클래스
 *
 * @author 박영준, 우혜승
 */
public class JwtUtil {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * JWT Token에서 만료시간을 추출하는 메서드
     *
     * @param token 문자열 JWT Token
     * @return 만료시간
     * @throws JsonProcessingException Json 파싱 중 오류 발생 예외
     */
    public static Long getExpireTime(String token) throws JsonProcessingException {

        String payload = token.split("\\.")[1];
        String payloadJson = new String(Base64.getDecoder().decode(payload));
        JsonNode jsonNode = OBJECT_MAPPER.readTree(payloadJson);
        return Long.parseLong(jsonNode.get("exp").asText());
    }

    /**
     * 현재 시간과 JWT Token의 만료 시간을 비교하여 재발급 여부를 판단하는 메서드
     *
     * @param token 문자열 JWT Token
     * @return 액세스 토큰이 만료되었으면 true, 아니면 false
     * @throws JsonProcessingException json 형태가 아니면 파싱 중 발생하는 예외
     */
    public static boolean requireReissue(String token) throws JsonProcessingException {

        Long expireTime = JwtUtil.getExpireTime(token);
        long now = new Date().getTime();

        return (now / 1000L) > expireTime;
    }
}