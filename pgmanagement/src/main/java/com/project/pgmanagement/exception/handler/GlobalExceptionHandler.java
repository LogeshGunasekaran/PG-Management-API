package com.project.pgmanagement.exception.handler;

import com.project.pgmanagement.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Map<String,String> errorMap;

    public GlobalExceptionHandler(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({AccountCreationException.class})
    public Map<String,String> creationFailed(AccountCreationException e)
    {
        errorMap.clear();
        errorMap.put("Field" , e.getField());
        errorMap.put("error message" , e.getUserDefinedMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({AccountDeleteException.class})
    public Map<String,String> deletionFailed(AccountDeleteException e)
    {
        errorMap.clear();
        errorMap.put("Field" , e.getField());
        errorMap.put("error message" , e.getUserDefinedMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({PgCreationException.class})
    public Map<String,String> pgCreationException(PgCreationException e)
    {
        errorMap.clear();
        errorMap.put("Field" , e.getField());
        errorMap.put("error message" , e.getUserDefinedMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RoomCreationException.class})
    public Map<String,String> roomCreationFailed(RoomCreationException e)
    {
        errorMap.clear();
        errorMap.put("Field" , e.getField());
        errorMap.put("error message" , e.getUserDefinedMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class})
    public Map<String,String> userNotFound(UserNotFoundException e)
    {
        errorMap.clear();
        errorMap.put("Field" , e.getField());
        errorMap.put("error message" , e.getUserDefinedMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> invalidInput(MethodArgumentNotValidException e)
    {
        errorMap.clear();
        errorMap.put("error message",e.getMessage());
        e.getFieldErrors().forEach(field->{
                                        errorMap.put(field.getField(),field.getRejectedValue().toString());
                }
                );
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String,String> invalidInput(ConstraintViolationException e)
    {
        errorMap.clear();

        e.getConstraintViolations().forEach(field->{
                    errorMap.put(field.getInvalidValue().toString(),field.getMessage());
                }
        );
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Map<String,String> dataIntegrity(SQLIntegrityConstraintViolationException e)
    {
        errorMap.clear();
        errorMap.put("SQL error code",String.valueOf(e.getErrorCode()));
        errorMap.put("cause",e.getMessage());
        errorMap.put("message","Enter Unique name & Password");
        return errorMap;
    }


}
