package com.peliculas.peliculas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());

        var statusCode = ex.getStatusCode();
        int statusValue = statusCode.value();

        // Obtener reason phrase de forma segura
        String reason;
        if (statusCode instanceof HttpStatus) {
            reason = ((HttpStatus) statusCode).getReasonPhrase();
        } else {
            HttpStatus resolved = HttpStatus.resolve(statusValue);
            reason = (resolved != null) ? resolved.getReasonPhrase() : statusCode.toString();
        }

        error.put("status", statusValue);
        error.put("error", reason);
        error.put("message", ex.getReason());

        return ResponseEntity.status(statusCode).body(error);
    }
}
