package br.com.duxusdesafio.config.exception;

import br.com.duxusdesafio.model.response.ErrorResponse;
import br.com.duxusdesafio.util.exception.DefaultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.PostConstruct;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @PostConstruct
    public void init() {}

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(DefaultException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.valueOf(ex.getStatus()).value(),
                HttpStatus.valueOf(ex.getStatus()).name(),
                ex.getMensagem() != null ? ex.getMensagem() : HttpStatus.valueOf(ex.getStatus()).name()
        );
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

}
