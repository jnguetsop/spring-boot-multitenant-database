package com.jnguetsop.multitenant.service.exception;

import lombok.Getter;

@Getter
public class MissingFieldException extends ValidationException {
    private final String fieldName;

    public MissingFieldException(String fieldName) {
        super("Argument %s is required but was not provided".formatted(fieldName));
        this.fieldName = fieldName;
    }
}
