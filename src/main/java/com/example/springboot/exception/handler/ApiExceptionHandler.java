package com.example.springboot.exception.handler;

import java.time.LocalDate;

import com.example.springboot.exception.ApiException;
import com.example.springboot.exception.ApiRequestException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        // Create payload containing exception details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        // ApiException apiException = new ApiException(e.getMessage(), e, badRequest, LocalDate.now());
        ApiException apiException = new ApiException(e.getMessage(), badRequest, LocalDate.now());
        return new ResponseEntity<>(apiException, badRequest);
    }
}
