package com.diego.tipocambio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = { TipoCambioException.class })
    public ResponseEntity<Object> handleApiRequestException(TipoCambioException e) {
        return ResponseEntity.badRequest()
                .body(ApiException.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .mensaje(e.getMessage())
                        .timestamp(ZonedDateTime.now())
                        .build());
    }

    @ExceptionHandler(value = { HttpMessageNotReadableException.class })
    public ResponseEntity<Object> handleApiRequestValidationException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest()
                .body(ApiException.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .mensaje("Payload inválido")
                        .timestamp(ZonedDateTime.now())
                        .build());
    }
}
