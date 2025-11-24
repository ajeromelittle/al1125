package com.example.aalittle.ToolRental.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, Object> errorResponse = new HashMap<>();
        Map<String,String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
                    fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
                });
        errorResponse.put("date", LocalDate.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST);
        errorResponse.put("message", fieldErrors);

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
