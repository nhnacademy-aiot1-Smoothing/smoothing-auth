package live.smoothing.auth.controller;

import live.smoothing.common.dto.ErrorResponse;
import live.smoothing.common.exception.CommonException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({CommonException.class})
    public ResponseEntity<ErrorResponse> error(HttpServletRequest request, CommonException e){

        return ResponseEntity.status(e.getStatus())
                .body(e.toEntity(request.getServletPath()));
    }
}
