package com.project.pgmanagement.exception;

import lombok.Getter;

@Getter
public class CredentialCreationException extends  RuntimeException{

        private String field;
        private String UserDefinedMessage;

    public CredentialCreationException(String field, String userDefinedMessage) {
        this.field = field;
        this.UserDefinedMessage = userDefinedMessage;
    }
}
