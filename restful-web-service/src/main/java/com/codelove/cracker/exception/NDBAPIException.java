package com.codelove.cracker.exception;

import org.springframework.http.HttpStatus;


public class NDBAPIException extends RuntimeException{

    private String message;

    private HttpStatus httpStatus;

    //private Integer statusCode; An INternal status code to be generated, need to implement

    public NDBAPIException(){

    }

    public NDBAPIException(String message, HttpStatus httpStatus){
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public NDBAPIException(String message, Throwable cause, HttpStatus httpStatus){
        super(message, cause);
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
