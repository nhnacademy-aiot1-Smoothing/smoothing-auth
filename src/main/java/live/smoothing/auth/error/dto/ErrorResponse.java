package live.smoothing.auth.error.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
