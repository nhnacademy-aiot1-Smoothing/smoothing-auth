package live.smoothing.auth.token.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReissueResponse {
    private String accessToken;
    private String tokenType;
}
