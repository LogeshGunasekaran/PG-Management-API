package com.project.pgmanagement.exception;

import lombok.Getter;

@Getter
public class PgCreationException extends RuntimeException {
    private String field;
    private String UserDefinedMessage;

    public PgCreationException(String field, String userDefinedMessage) {
        this.field = field;
        UserDefinedMessage = userDefinedMessage;
    }
}
