package live.smoothing.auth.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorException extends RuntimeException {

    private LocalDateTime timeStamp = LocalDateTime.now();
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ErrorException(Integer status, Class c, String message, String path) {

        this.status = status;
        this.error = c.getName();
        this.message = message;
        this.path = path;
    }
}
