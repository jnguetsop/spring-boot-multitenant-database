package com.jnguetsop.multitenant.web.rest;

import com.jnguetsop.multitenant.service.exception.ValidationException;
import com.jnguetsop.multitenant.web.error.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDTO> handleValidationException(ValidationException exception) {
        log.error("Validation exception", exception);
        return ResponseEntity.badRequest().body(new ErrorDTO(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, NativeWebRequest request) {
        log.error("Exception", exception);
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), BAD_REQUEST, request);
    }
}
