package live.smoothing.auth.exception;

import live.smoothing.auth.error.dto.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorException extends RuntimeException {

    private LocalDateTime timeStamp = LocalDateTime.now().withNano(0);
    private Integer status;
    private Class error;
    private String message;

    public ErrorException(Integer status, Class c,
                          String message) {

        this.status = status;
        this.error = c;
        this.message = message;
    }

    public ErrorResponse toEntity(HttpServletRequest request) {

        return new ErrorResponse(this.timeStamp, this.status,
                this.error.getName(), this.message, request.getServletPath());
    }
}
