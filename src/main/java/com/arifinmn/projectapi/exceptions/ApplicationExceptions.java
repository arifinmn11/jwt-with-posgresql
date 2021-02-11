package com.arifinmn.projectapi.exceptions;

import org.springframework.http.HttpStatus;

public class ApplicationExceptions extends RuntimeException {
    HttpStatus status;

    public ApplicationExceptions(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
