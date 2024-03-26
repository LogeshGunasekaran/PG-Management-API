package com.project.pgmanagement.exception;

public class AdminCreationFailedException extends RuntimeException {

    private String field;
    private String UserDefinedMessage;

    public AdminCreationFailedException(String field, String userDefinedMessage) {
        this.field = field;
        this.UserDefinedMessage = userDefinedMessage;
    }
}
