package com.arifinmn.projectapi.exceptions;

import org.springframework.http.HttpStatus;

public class PathNotFoundException extends ApplicationExceptions {

    public PathNotFoundException() {
        super(HttpStatus.NOT_FOUND, "error." + HttpStatus.NOT_FOUND.value() + ".entity");
    }
}
