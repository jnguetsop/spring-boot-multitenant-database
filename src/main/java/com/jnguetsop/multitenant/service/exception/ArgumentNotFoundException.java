package com.jnguetsop.multitenant.service.exception;

public class ArgumentNotFoundException extends ValidationException {
    public ArgumentNotFoundException(String message) {
        super(message);
    }
}
