package com.codelove.cracker.errors;

import org.springframework.http.HttpStatus;

public class GenericError {

    private String message;

    private HttpStatus httpStatus;

    public GenericError() {
    }

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
