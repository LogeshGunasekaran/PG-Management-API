package com.project.pgmanagement.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private String field;
    private String userDefinedMessage;

    public UserNotFoundException(String field, String userDefinedMessage) {
        this.field = field;
        this.userDefinedMessage = userDefinedMessage;
    }


}
