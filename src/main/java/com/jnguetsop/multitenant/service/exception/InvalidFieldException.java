package com.jnguetsop.multitenant.service.exception;

public class InvalidFieldException extends ValidationException {
    private final String rejectedFieldName;
    private final String rejectedFieldValue;

    public InvalidFieldException(String message, String rejectedFieldName, String rejectedFieldValue) {
        super(message);
        this.rejectedFieldName = rejectedFieldName;
        this.rejectedFieldValue = rejectedFieldValue;
    }
}
