package com.project.pgmanagement.exception;

import lombok.Getter;

@Getter
public class VacancyUpdationException extends RuntimeException {
    private String field;
    private String UserDefinedMessage;

    public VacancyUpdationException(String field, String userDefinedMessage) {
        this.field = field;
        UserDefinedMessage = userDefinedMessage;
    }
}
