package com.arifinmn.projectapi.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends ApplicationExceptions {

    public EntityNotFoundException() {
        super(HttpStatus.NOT_FOUND, "error." + HttpStatus.NOT_FOUND.value() + ".entity");
    }
}

