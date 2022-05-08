package com.jnguetsop.multitenant.service.exception;

import lombok.Getter;

@Getter
public class FieldInUseException extends ValidationException {
    private final String duplicatedFieldName;
    private final String duplicatedFieldValue;

    public FieldInUseException(String duplicatedFieldName, String duplicatedFieldValue) {
        super("The field %s with value %s is already in use.".formatted(duplicatedFieldName, duplicatedFieldValue));
        this.duplicatedFieldName = duplicatedFieldName;
        this.duplicatedFieldValue = duplicatedFieldValue;
    }
}
