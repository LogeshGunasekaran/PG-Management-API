package com.project.pgmanagement.exception;

import lombok.Getter;

@Getter
public class AccountCreationException extends  RuntimeException{
    private String field;
    private String UserDefinedMessage;

    public AccountCreationException(String field, String userDefinedMessage) {
        this.field = field;
       this.UserDefinedMessage = userDefinedMessage;
    }
}
