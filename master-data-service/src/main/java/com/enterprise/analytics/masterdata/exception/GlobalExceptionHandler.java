package com.enterprise.analytics.masterdata.exception;

import com.enterprise.analytics.masterdata.response.EAJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<EAJson<?>> handleIllegalState(IllegalStateException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EAJson.error(ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<EAJson<?>> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(EAJson.error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<EAJson<?>> handleException(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(EAJson.error("Unexpected server error"));
    }

}
