package live.smoothing.auth.controller;

import live.smoothing.auth.error.dto.ErrorResponse;
import live.smoothing.auth.exception.ErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({ErrorException.class})
    public ResponseEntity<ErrorResponse> error(HttpServletRequest request, ErrorException e){

        return ResponseEntity.status(e.getStatus())
                .body(e.toEntity(request));
    }
}
