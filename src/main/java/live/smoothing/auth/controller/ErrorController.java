package live.smoothing.auth.controller;

import live.smoothing.common.dto.ErrorResponse;
import live.smoothing.common.exception.CommonException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
/**
 * Error 처리를 위한 컨트롤러 클래스
 *
 * @author 우혜승
 */
@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({CommonException.class})
    public ResponseEntity<ErrorResponse> error(HttpServletRequest request, CommonException e){

        return ResponseEntity.status(e.getStatus())
                .body(e.toEntity(request.getServletPath()));
    }
}
