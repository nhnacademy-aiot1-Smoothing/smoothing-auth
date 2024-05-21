package live.smoothing.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class InnerUserDto {
    private String userId;
    private String userPassword;
}
