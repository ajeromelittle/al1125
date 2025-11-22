package com.example.aalittle.ToolRental.validation;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String,Object>> handleValidationException(HandlerMethodValidationException ex) {

        Map<String, Object> errorResponse = new HashMap<>();
        Map<String,String> fieldErrors = new HashMap<>();

        ex.getParameterValidationResults().forEach(validationResult -> {
            validationResult.getResolvableErrors().forEach(error -> {
                if(error instanceof FieldError) {
                    FieldError fieldError = (FieldError) error;
                    fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }

                });
        });
        errorResponse.put("date", LocalDate.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST);
        errorResponse.put("message", fieldErrors);

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
