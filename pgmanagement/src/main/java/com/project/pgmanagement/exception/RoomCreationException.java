package com.project.pgmanagement.exception;

import lombok.Getter;

@Getter
public class RoomCreationException extends RuntimeException {
    private String field;
    private String UserDefinedMessage;

    public RoomCreationException(String field, String userDefinedMessage) {
        this.field = field;
        UserDefinedMessage = userDefinedMessage;
    }
}
