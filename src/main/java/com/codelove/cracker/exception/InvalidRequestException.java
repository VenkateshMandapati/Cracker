package com.codelove.cracker.exception;

import com.codelove.cracker.errors.RequestError;
import org.springframework.http.HttpStatus;

import java.util.List;

public class InvalidRequestException extends RuntimeException{

    private HttpStatus httpStatus;

    private String message = "Invalid request parameters, please check for below errors";

    private List<RequestError> requestErrorList;

    public InvalidRequestException(){

    }

    public InvalidRequestException(List<RequestError> requestErrorList, HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        this.requestErrorList = requestErrorList;
    }

    public InvalidRequestException(String message, List<RequestError> requestErrorList, HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        this.message = message;
        this.requestErrorList = requestErrorList;
    }

    public InvalidRequestException(String message, HttpStatus httpStatus, Throwable throwable){
        super(throwable);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<RequestError> getRequestErrorList() {
        return requestErrorList;
    }

    public void setRequestErrorList(List<RequestError> requestErrorList) {
        this.requestErrorList = requestErrorList;
    }
}
