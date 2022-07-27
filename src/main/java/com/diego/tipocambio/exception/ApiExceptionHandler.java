package com.diego.tipocambio.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
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

    @ExceptionHandler(value = { AuthenticationException.class })
    public ResponseEntity<Object> handleApiAuthException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiException.builder()
                        .httpStatus(HttpStatus.UNAUTHORIZED)
                        .mensaje("Credeenciales de usuario inválidas")
                        .timestamp(ZonedDateTime.now())
                        .build());
    }

    @ExceptionHandler(value = { JWTVerificationException.class })
    public ResponseEntity<Object> handleApiJWTException(JWTVerificationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiException.builder()
                        .httpStatus(HttpStatus.UNAUTHORIZED)
                        .mensaje("Token JWT inválido")
                        .timestamp(ZonedDateTime.now())
                        .build());
    }
}
