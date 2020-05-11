package com.restaurant.exception;

public enum ErrorTypes {
    EMAIL_INPUT_ERROR(""),
    NOT_EQUAL_PASSWORDS("not_equal_passwords"),
    OBJECT_IS_NULL("object_is_null");

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    ErrorTypes(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
