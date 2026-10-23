package org.example.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class DatabaseExceptionHandler {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(DatabaseExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraint(DataIntegrityViolationException exception) {
        log.debug("Database constraint rejected a write", exception);
        return ResponseEntity.status(409).body(Map.of("error",
                "Data conflicts with an existing record or a database constraint"));
    }
}
