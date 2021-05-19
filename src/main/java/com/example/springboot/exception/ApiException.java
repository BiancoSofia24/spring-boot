package com.example.springboot.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

public class ApiException {
    
    private final String message;
    // private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final LocalDate timestamp;

    public ApiException(String message, HttpStatus httpStatus, LocalDate timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    // The Throwable allows me to see the full exception details
    // public ApiException(String message, Throwable throwable, HttpStatus httpStatus, LocalDate timestamp) {
    //     this.message = message;
    //     this.throwable = throwable;
    //     this.httpStatus = httpStatus;
    //     this.timestamp = timestamp;
    // }

    public String getMessage() {
        return this.message;
    }

    // public Throwable getThrowable() {
    //     return this.throwable;
    // }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public LocalDate getTimestamp() {
        return this.timestamp;
    }
}
